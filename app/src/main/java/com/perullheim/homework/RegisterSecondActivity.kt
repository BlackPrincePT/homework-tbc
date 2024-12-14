package com.perullheim.homework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.perullheim.homework.databinding.ActivityRegisterSecondBinding

class RegisterSecondActivity : AppCompatActivity() {

    private val binding: ActivityRegisterSecondBinding by lazy {
        ActivityRegisterSecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }
    }
}