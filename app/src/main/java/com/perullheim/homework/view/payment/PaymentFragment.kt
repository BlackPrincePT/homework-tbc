package com.perullheim.homework.view.payment

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentPaymentBinding
import com.perullheim.homework.helper.ViewBindingFragment

class PaymentFragment :
    ViewBindingFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {

    private val viewModel: PaymentViewModel by navGraphViewModels(R.id.nav_graph)

    private val cardAdapter = CardAdapter { card ->
        PaymentFragmentDirections.actionPaymentFragmentToDeleteCardDialogFragment(card)
            .let { action ->
                Navigation.findNavController(binding.root).navigate(action)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpCards.adapter = cardAdapter

        viewModel.cards.observe(viewLifecycleOwner) {
            cardAdapter.submitList(it)
        }

        PaymentFragmentDirections.actionPaymentFragmentToAddNewCardFragment().let { action ->
            binding.tvAddNew.setOnClickListener {
                Navigation.findNavController(view).navigate(action)
            }
        }
    }
}