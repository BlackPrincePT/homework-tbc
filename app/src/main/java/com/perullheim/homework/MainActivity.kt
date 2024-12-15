package com.perullheim.homework

import android.content.Intent
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

        updateWelcomeText()
    }

    private fun updateWelcomeText() {
        UserManager.currentUserData?.username?.let { username ->
            binding.tvHelloText.text = getString(R.string.hello_text, username)
        }
    }

    private fun setupButtonListeners() {
        binding.tvLogOut.setOnClickListener {
            UserManager.logout() {
                segueToWelcomeActivity()
            }
        }

        binding.tvDeleteAccount.setOnClickListener {
            UserManager.deleteAccount() {
                segueToWelcomeActivity()
            }
        }
    }

    private fun segueToWelcomeActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
}