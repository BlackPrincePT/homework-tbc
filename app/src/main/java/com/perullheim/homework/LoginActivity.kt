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

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnLogIn.setOnClickListener {
            loginClicked()
        }
    }

    private fun loginClicked() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        UserManager.login(email, password) { isRegistered ->
            if (isRegistered) {
                segueToMainActivity()
            } else {
                segueToRegisterSecondActivity()
            }
        }
    }

    private fun segueToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun segueToRegisterSecondActivity() {
        val intent = Intent(this, RegisterSecondActivity::class.java)
        startActivity(intent)
    }
}