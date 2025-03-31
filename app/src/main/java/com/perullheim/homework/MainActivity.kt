package com.perullheim.homework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.perullheim.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setupButtonListeners()
        setupStatusListeners()
    }

    private fun addUser() {
        createUser()?.let { newUser ->
            disableAllButtons()
            UserManager.addUser(newUser, statusFn)
        }
    }

    private fun removeUser() {
        val email = binding.etEmail.text.toString()

        if (!isEmailValid(email)) {
            updateStatusText("Email badly formatted")
            return
        }

        disableAllButtons()

        UserManager.removeUser(email, statusFn)
    }

    private fun updateUser() {
        val email = binding.etEmail.text.toString()

        createUser()?.let { newUser ->
            disableAllButtons()
            UserManager.updateUser(email, newUser, statusFn)
        }
    }

    // ========= Setup Functions =========

    private fun setupStatusListeners() {
        UserManager.subscribeToUserCount(path = "users-active") { count ->
            binding.tvAddedUsers.text = count.toString()
        }

        UserManager.subscribeToUserCount(path = "users-removed") { count ->
            binding.tvRemovedUsers.text = count.toString()
        }
    }

    private fun setupButtonListeners() {
        binding.btnAddUser.setOnClickListener {
            areTextFieldsEmpty()
            addUser()
        }

        binding.btnRemoveUser.setOnClickListener {
            areTextFieldsEmpty()
            removeUser()
        }

        binding.btnUpdateUser.setOnClickListener {
            areTextFieldsEmpty()
            updateUser()
        }
    }

    // ========= Helper Functions =========

    private fun createUser(): User? {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()

        val age = try {
            binding.etAge.text.toString().toInt()
        } catch (error: NumberFormatException) {
            return null
        }

        if (!isEmailValid(email)) {
            updateStatusText("Email badly formatted")
            return null
        }

        val user = User(
            firstName = firstName,
            lastName = lastName,
            age = age,
            email = email
        )

        return user
    }

    private val statusFn = { message: String ->
        updateStatusText(message)
        enableAllButtons()
    }

    private fun updateStatusText(message: String) {
        val textColor =
            if ("successfully" in message) getColor(R.color.green) else getColor(R.color.red)
        with(binding) {
            tvStatusInfo.setTextColor(textColor)
            tvStatusInfo.text = message
        }
    }

    private fun enableAllButtons() {
        with(binding) {
            btnAddUser.isEnabled = true
            btnRemoveUser.isEnabled = true
            btnRemoveUser.isEnabled = true
        }
    }

    private fun disableAllButtons() {
        with(binding) {
            btnAddUser.isEnabled = false
            btnRemoveUser.isEnabled = false
            btnRemoveUser.isEnabled = false
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailParts = email.split('@', '.')

        return (emailParts.count() == 3 && emailParts.none { it.isEmpty() } && email[emailParts.first().length] == '@')
    }

    private fun areTextFieldsEmpty(): Boolean {
        val fields = listOf(binding.etFirstName, binding.etLastName, binding.etAge, binding.etEmail)

        if (fields.any { it.text.toString().isEmpty() }) {
            updateStatusText("Fill out all fields")
            return false
        } else
            return true
    }
}