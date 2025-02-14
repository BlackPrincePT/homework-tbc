package com.perullheim.homework.presentation.resorts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.perullheim.homework.databinding.FragmentResortsBinding
import com.perullheim.homework.utils.ViewBindingFragment
import com.perullheim.homework.utils.collectLatest
import com.perullheim.homework.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class ResortsFragment :
    ViewBindingFragment<FragmentResortsBinding>(FragmentResortsBinding::inflate) {

    private val viewModel: ResortsViewModel by viewModels()
    private val resortsAdapter = ResortsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpResorts.apply {
            adapter = resortsAdapter
            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                val scaleFactor = 0.85f + (1 - abs(position)) * 0.15f
                page.scaleY = scaleFactor
                page.alpha = 0.5f + (1 - abs(position)) * 0.5f
                val pageMarginPx = -40
                when {
                    position < -1 -> {
                        page.translationX = -page.width.toFloat()
                    }

                    position <= 1 -> {
                        val offset = position * -(pageMarginPx)
                        page.translationX = offset
                    }

                    else -> {
                        page.translationX = page.width.toFloat()
                    }
                }
            }
        }

        collectLatest(viewModel.resorts) {
            resortsAdapter.submitList(it)
        }

        collectLatest(viewModel.errorMessage) {
            view.showSnackBar(it)
        }
    }
}