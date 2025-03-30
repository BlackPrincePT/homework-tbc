package com.perullheim.homework.presentation.screen.payment

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.databinding.FragmentPaymentBinding
import com.perullheim.homework.presentation.core.base.BaseFragment
import com.perullheim.homework.presentation.core.extension.collectLatest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {

    private val viewModel: PaymentViewModel by viewModels()

    companion object {
        const val ACCOUNT_REQUEST_KEY = "account_request_key"
        const val ACCOUNT_RESULT_KEY = "account_result_key"
    }

    override fun setup() {
        subscribeToViewStateEffects()

        binding.root.setContent {
            PaymentScreen(viewModel)
        }

        setFragmentResultListener(ACCOUNT_REQUEST_KEY) { _, bundle ->
            bundle.getString(ACCOUNT_RESULT_KEY)?.let {
                viewModel.onEvent(PaymentUiEvent.OnAccountSelected(it))
            }
        }
    }

    private fun subscribeToViewStateEffects() {
        collectLatest(viewModel.uiEffect) { effect ->
            when (effect) {
                PaymentUiEffect.NavigateToFindAccount -> navigateToFindAccount()
                PaymentUiEffect.NavigateToMyAccounts -> navigateToMyAccounts()
            }
        }
    }

    private fun navigateToMyAccounts() {
        findNavController().navigate(PaymentFragmentDirections.paymentToAccounts())
    }

    private fun navigateToFindAccount() {
        findNavController().navigate(PaymentFragmentDirections.paymentToFindAccount())
    }
}