package com.perullheim.homework.presentation.payment

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.perullheim.homework.databinding.FragmentPaymentBinding
import com.perullheim.homework.presentation.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {

    private val viewModel: PaymentViewModel by viewModels()

    companion object {
        const val ACCOUNT_REQUEST_KEY = "account_request_key"
        const val ACCOUNT_RESULT_KEY = "account_result_key"
    }

    override fun setup() {
        setFragmentResultListener(ACCOUNT_REQUEST_KEY) { _, bundle ->
            bundle.getString(ACCOUNT_RESULT_KEY)?.let {
                viewModel.onEvent(PaymentUiEvent.OnAccountSelected(it))
            }
        }

        binding.root.setContent {
            PaymentScreen(viewModel)
        }
    }
}