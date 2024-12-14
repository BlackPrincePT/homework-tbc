package com.perullheim.homework

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

        binding.imgBack.setOnClickListener {
            finish()
        }
    }
}