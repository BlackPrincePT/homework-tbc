package com.perullheim.homework.data.remote.model.mapper

import com.perullheim.homework.data.remote.model.AccountCheckDto
import com.perullheim.homework.domain.core.ToDomainMapper
import javax.inject.Inject

class AccountCheckDtoMapper @Inject constructor() : ToDomainMapper<AccountCheckDto, Boolean> {

    override fun mapToDomain(data: AccountCheckDto): Boolean {
        return AccountCheckDto.Status.valueOf(data.status).value
    }
}