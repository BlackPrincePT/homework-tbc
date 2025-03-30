package com.perullheim.homework.presentation.screen.find

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.databinding.FragmentFindAccountBinding
import com.perullheim.homework.domain.core.Error
import com.perullheim.homework.presentation.core.base.BaseBottomSheetFragment
import com.perullheim.homework.presentation.core.error.toStringResId
import com.perullheim.homework.presentation.core.extension.collectLatest
import com.perullheim.homework.presentation.core.extension.showSnackBar
import com.perullheim.homework.presentation.model.PaymentOption
import com.perullheim.homework.presentation.screen.payment.PaymentFragment.Companion.ACCOUNT_REQUEST_KEY
import com.perullheim.homework.presentation.screen.payment.PaymentFragment.Companion.FIND_ACCOUNT_RESULT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindAccountFragment :
    BaseBottomSheetFragment<FragmentFindAccountBinding>(FragmentFindAccountBinding::inflate) {

    private val viewModel: FindAccountViewModel by viewModels()

    companion object {
        private const val FAKE_ID = "123"
    }

    override fun setup() {
        setupSpinner()
        subscribeToViewEffectUpdates()
    }

    override fun listeners() = with(binding) {
        btnFind.setOnClickListener {
            viewModel.onEvent(FindAccountUiEvent.OnFind(text = etAccountNumber.text.toString()))
        }
    }

    private fun setupSpinner() = with(binding) {
        val paymentOptions = PaymentOption.entries.map { it.displayName }

        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, paymentOptions).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPaymentOptions.adapter = this
        }

        spinnerPaymentOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                val selectedOption = PaymentOption.entries[position]

                viewModel.onEvent(FindAccountUiEvent.OnPaymentOptionChanged(newOption = selectedOption))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun subscribeToViewEffectUpdates() {
        collectLatest(viewModel.uiEffect) { effect ->
            when (effect) {
                is FindAccountUiEffect.Failure -> showError(error = effect.error)
                FindAccountUiEffect.NavigateBack -> navigateBack()
            }
        }
    }

    private fun navigateBack() {
        setFragmentResult(ACCOUNT_REQUEST_KEY, bundleOf(FIND_ACCOUNT_RESULT_KEY to FAKE_ID))
        findNavController().navigateUp()
    }

    private fun showError(error: Error) {
        val errorMsg = getString(error.toStringResId())
        binding.root.showSnackBar(errorMsg)
    }
}