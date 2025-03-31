package com.perullheim.homework

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
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

        setupTexts()
    }

    private fun setupTexts() {
        binding.tvLocationName.text = getString(R.string.andes_mountain)
        binding.tvLocationPlace.text = getString(R.string.south_america)
        binding.tvLocationPrice.text = getString(R.string.price)
        binding.tvLocationPriceCurrency.text = "$"
        binding.tvLocationPriceAmount.text = 230.toString()
        binding.tvOverview.text = getString(R.string.overview)
        binding.tvDetails.text = getString(R.string.details)
        binding.tvTime.text = getString(R.string.time, 8)
        binding.tvTemperature.text = getString(R.string.temperature, 16)
        binding.tvRating.text = 4.5.toString()
        binding.tvBookNow.text = getString(R.string.book_now)
        binding.tvLocationInfo.text = getString(R.string.location_info)
    }
}