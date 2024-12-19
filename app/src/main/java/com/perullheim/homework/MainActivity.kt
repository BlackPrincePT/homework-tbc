package com.perullheim.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.perullheim.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val supportManager = supportFragmentManager
        val transaction = supportManager.beginTransaction().replace(R.id.main, WelcomeFragment(), "WelcomeFragment")
        transaction.commit()
    }
}