package com.perullheim.homework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.perullheim.homework.databinding.ActivityRegisterSecondBinding

class RegisterSecondActivity : AppCompatActivity() {

    private val binding: ActivityRegisterSecondBinding by lazy {
        ActivityRegisterSecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        binding.imgBack.setOnClickListener {
            UserManager.logout() {
                finish()
            }
        }

        binding.btnSignUp.setOnClickListener {
            signUpClicked()
        }
    }


    private fun signUpClicked() {
        val username = binding.etUsername.text.toString()

        UserManager.createUser(username) {
            segueToMainActivity()
        }
    }

    private fun segueToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}