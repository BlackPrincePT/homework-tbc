package com.perullheim.homework.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.databinding.FragmentLockBinding
import com.perullheim.homework.domain.model.NumpadKey
import com.perullheim.homework.utils.ViewBindingFragment
import com.perullheim.homework.utils.showSnackBar
import com.perullheim.homework.utils.viewLifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LockFragment : ViewBindingFragment<FragmentLockBinding>(FragmentLockBinding::inflate) {

    private val viewModel: LockViewModel by viewModels()
    private val enteredDigitsAdapter = EnteredDigitsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupNumpad()
        setupCollectors()
    }

    private fun setupRecyclerView() {
        binding.rvEnteredDigits.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = enteredDigitsAdapter
        }

        binding.rvEnteredDigits.itemAnimator = null
    }

    private fun setupNumpad() {
        NumpadKey.entries.forEach { numpadType ->
            val numpadKey = NumpadKeyView(requireContext()).apply {
                setIcon(numpadType)
                setClickListener {
                    when (numpadType) {
                        NumpadKey.BACKSPACE -> viewModel.removeLastDigit()
                        NumpadKey.TOUCH_ID -> {}
                        else -> viewModel.addDigit(numpadType.value)
                    }
                }
            }

            binding.numpadLayout.addView(numpadKey)
        }
    }

    private fun setupCollectors() {
        viewLifecycleScope {
            launch {
                viewModel.passcodeFlow.collectLatest {
                    enteredDigitsAdapter.submitList(it.toList())
                }
            }
            launch {
                viewModel.message.collectLatest {
                    binding.root.showSnackBar(it)
                }
            }
        }
    }
}