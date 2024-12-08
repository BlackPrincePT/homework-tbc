package com.perullheim.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.perullheim.homework.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private var newUsers = mutableSetOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnAddUser.setOnClickListener {
            addUser()
        }

        binding.tvCheckExistingUser.setOnClickListener {
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        UsersData.users.addAll(newUsers)
    }

    // ========= Helper Functions =========

    private fun addUser() {
        val fullName = binding.etFullName.text.toString()
        val email = binding.etRegisterEmail.text.toString().lowercase()

        // Clear Text Fields
        binding.etFullName.text = null
        binding.etRegisterEmail.text = null

        // Validate Entered Info
        binding.errorRegister.text = when {
            fullName.isEmpty() || email.isEmpty() -> "Please fill out all of the fields!"
            !isFullNameValid(fullName) -> "Full Name is not valid!"
            !email.validateForEmail() -> "Email is not valid!"
            else -> {
                // Check If A User With The Same Email Already Exists
                if (UsersData.users.none { it.email == email }) {
                    val newUser = User(fullName, email)
                    newUsers.add(newUser)
                }

                null
            }
        }
    }

    private fun isFullNameValid(fullName: String): Boolean {
        val fullNameParts = fullName.split(' ')

        return (fullNameParts.count() > 1 && fullNameParts.none { it.isEmpty() && it.length < 2 })
    }
}