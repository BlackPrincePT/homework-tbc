package com.perullheim.homework.data.remote.model.mapper

import com.perullheim.homework.data.remote.model.AccountDto
import com.perullheim.homework.domain.core.ToDomainMapper
import com.perullheim.homework.domain.model.Account
import com.perullheim.homework.domain.model.CardType
import com.perullheim.homework.domain.model.Currency
import javax.inject.Inject

class AccountDtoMapper @Inject constructor() : ToDomainMapper<AccountDto, Account> {

    override fun mapToDomain(data: AccountDto): Account {
        return Account(
            id = data.id,
            accountName = data.accountName,
            currency = Currency.valueOf(data.currency),
            cardType = CardType.valueOf(data.cardType),
            balance = data.balance,
            cardLogoUrl = data.cardLogoUrl
        )
    }
}