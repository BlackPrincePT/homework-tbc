package com.perullheim.homework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.perullheim.homework.databinding.ActivityRegisterFirstBinding

class RegisterFirstActivity : AppCompatActivity() {

    private val binding: ActivityRegisterFirstBinding by lazy {
        ActivityRegisterFirstBinding.inflate(layoutInflater)
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

        binding.btnNext.setOnClickListener {
            nextClicked()
        }
    }

    private fun nextClicked() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        UserManager.register(email, password) {
            segueToRegisterSecondActivity()
        }
    }

    private fun segueToRegisterSecondActivity() {
        val intent = Intent(this, RegisterSecondActivity::class.java)
        startActivity(intent)
    }
}