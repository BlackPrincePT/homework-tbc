package com.perullheim.homework.presentation.payment

import androidx.navigation.fragment.findNavController
import com.perullheim.homework.databinding.FragmentPaymentBinding
import com.perullheim.homework.presentation.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {

    override fun setup() {
        binding.root.setOnClickListener {
            findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToAccountsFragment())
        }
    }
}