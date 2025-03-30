package com.perullheim.homework.presentation.model.mapper

import com.perullheim.homework.domain.core.FromDomainMapper
import com.perullheim.homework.domain.model.Account
import com.perullheim.homework.presentation.model.UiAccount
import javax.inject.Inject

class UiAccountMapper @Inject constructor(): FromDomainMapper<UiAccount, Account> {

    override fun mapFromDomain(data: Account): UiAccount {
        return UiAccount(
            id = data.id,
            accountName = data.accountName,
            iban = data.iban,
            currency = data.currency,
            cardType = data.cardType,
            balance = data.balance,
            cardLogoUrl = data.cardLogoUrl
        )
    }
}