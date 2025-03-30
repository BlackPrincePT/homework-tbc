package com.perullheim.homework.presentation.screen.payment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.databinding.FragmentPaymentBinding
import com.perullheim.homework.domain.core.Error
import com.perullheim.homework.presentation.core.base.BaseFragment
import com.perullheim.homework.presentation.core.error.toStringResId
import com.perullheim.homework.presentation.core.extension.collectLatest
import com.perullheim.homework.presentation.core.extension.showSnackBar
import com.perullheim.homework.presentation.model.UiAccount
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {

    private val viewModel: PaymentViewModel by viewModels()

    companion object {
        const val ACCOUNT_REQUEST_KEY = "account_request_key"
        const val MY_ACCOUNT_RESULT_KEY = "my_account_result_key"
        const val FIND_ACCOUNT_RESULT_KEY = "find_account_result_key"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun setup() {
        subscribeToViewStateEffects()

        binding.root.setContent {
            PaymentScreen(viewModel)
        }

        setFragmentResultListener(ACCOUNT_REQUEST_KEY) { _, bundle ->
            bundle.getSerializable(MY_ACCOUNT_RESULT_KEY, UiAccount::class.java)?.let {
                viewModel.onEvent(PaymentUiEvent.OnAccountSelected(it))
            }

            bundle.getString(FIND_ACCOUNT_RESULT_KEY)?.let {
                viewModel.onEvent(PaymentUiEvent.OnAccountFound(it))
            }
        }
    }

    private fun subscribeToViewStateEffects() {
        collectLatest(viewModel.uiEffect) { effect ->
            when (effect) {
                is PaymentUiEffect.Failure -> showError(error = effect.error)
                PaymentUiEffect.NavigateToFindAccount -> navigateToFindAccount()
                PaymentUiEffect.NavigateToMyAccounts -> navigateToMyAccounts()
            }
        }
    }

    private fun showError(error: Error) {
        val errorMsg = getString(error.toStringResId())
        binding.root.showSnackBar(errorMsg)
    }

    private fun navigateToMyAccounts() {
        findNavController().navigate(PaymentFragmentDirections.paymentToAccounts())
    }

    private fun navigateToFindAccount() {
        findNavController().navigate(PaymentFragmentDirections.paymentToFindAccount())
    }
}