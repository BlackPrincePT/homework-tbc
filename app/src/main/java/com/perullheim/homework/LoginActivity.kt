package com.perullheim.homework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.perullheim.homework.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnGetUserInfo.setOnClickListener {
            checkUser()
        }

        binding.tvCreateNewUser.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        updateUserCount()
    }

    private fun updateUserCount() {
        val userCount = "Users -> ${UsersData.users.count()}"
        binding.tvUsers.text = userCount
    }

    private fun checkUser() {
        val enteredEmail = binding.etLoginEmail.text.toString().lowercase()

        // Clear User Info and Email Field
        binding.tvUserInfo.text = null
        binding.etLoginEmail.text = null

        if (!enteredEmail.validateForEmail()) {
            val errorText = "Email is not valid!"
            binding.errorLogin.text = errorText
            return
        }

        val user = UsersData.users.firstOrNull { it.email == enteredEmail }

        if (user != null) {
            val userInfo = "Full Name: ${user.fullName}\nEmail: ${user.email}"
            binding.tvUserInfo.text = userInfo
        } else {
            val errorText = "Such user doesn't exist!"
            binding.errorLogin.text = errorText
        }
    }
}