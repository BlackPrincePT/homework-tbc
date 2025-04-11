package com.perullheim.homework.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentAddNewCardBinding
import com.perullheim.homework.helper.ViewBindingFragment
import com.perullheim.homework.model.Card
import com.perullheim.homework.model.CardType
import com.perullheim.homework.view.card.VisaCardFragmentDirections
import com.perullheim.homework.view.payment.PaymentViewModel
import kotlin.properties.Delegates

class AddNewCardFragment :
    ViewBindingFragment<FragmentAddNewCardBinding>(FragmentAddNewCardBinding::inflate) {

    private val viewModel: PaymentViewModel by viewModels(
        { findNavController().getBackStackEntry(R.id.nav_graph) }
    )

    private var currentCardType: CardType by Delegates.observable(CardType.MASTERCARD) { _, _, new ->
        when (new) {
            CardType.MASTERCARD -> VisaCardFragmentDirections.actionGlobalMastercardCardFragment()
            CardType.VISA -> VisaCardFragmentDirections.actionGlobalVisaCardFragment()
        }.let { action ->
            Navigation.findNavController(binding.viewCardContainer).navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextListeners()
        setupClickListeners()
    }

    private fun setupTextListeners() {
        with(binding) {
            etCardNumber.addTextChangedListener {
                etCardNumber.text?.let { text ->
                    currentCardType = CardType.getTypeFrom(text.firstOrNull())
                }
            }

            etExpires.addTextChangedListener {
                etExpires.text?.let { text ->
                    if (text.count() == 2)
                        etExpires.text?.append('/')
                }
            }
        }
    }

    private fun setupClickListeners() {
        with(binding) {
            btnAddCard.setOnClickListener {
                Card(
                    cardholderName = etCardholderName.text.toString(),
                    cardNumber = etCardNumber.text.toString(),
                    expirationDate = etExpires.text.toString(),
                    cvv = etCVV.text.toString()
                ).let {
                    viewModel.addNewCard(it)
                    parentFragmentManager.popBackStack()
                }
            }
        }
    }
}