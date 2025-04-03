package com.perullheim.homework.domain.repository

import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.model.Session

interface RegisterRepository {
    suspend fun register(email: String, password: String): Resource<Session, DataError.Network>
}