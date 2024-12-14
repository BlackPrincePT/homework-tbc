package com.perullheim.homework

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
            next()
        }
    }

    private fun next() {
        val intent = Intent(this, RegisterSecondActivity::class.java)
        startActivity(intent)
    }
}