package com.perullheim.homework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.perullheim.homework.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private val binding: ActivityWelcomeBinding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        binding.btnLogIn.setOnClickListener {
            login()
        }
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun login() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun register() {
        val intent = Intent(this, RegisterFirstActivity::class.java)
        startActivity(intent)
    }
}