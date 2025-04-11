package com.perullheim.homework.view.payment

import android.widget.ListAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perullheim.homework.model.Card
import java.util.Date

class PaymentViewModel : ViewModel() {

    val cards = MutableLiveData<List<Card>>()

    fun addNewCard(card: Card) {
        val updatedCards = cards.value.orEmpty().toMutableList()
        updatedCards.add(0, card)
        cards.value = updatedCards
    }

    fun removeCard(card: Card) {
        val updatedCards = cards.value.orEmpty().toMutableList()
        updatedCards.remove(card)
        cards.value = updatedCards
    }
}