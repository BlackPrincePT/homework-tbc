package com.perullheim.homework.domain.usecase.login

import com.perullheim.homework.domain.cache.CacheManager
import com.perullheim.homework.domain.cache.PreferenceKeys
import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.map
import com.perullheim.homework.domain.core.onSuccessAsync
import com.perullheim.homework.domain.model.Session
import com.perullheim.homework.domain.repository.LoginRepository
import com.perullheim.homework.domain.repository.SessionRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sessionRepository: SessionRepository,
    private val cacheManager: CacheManager
) {
    suspend operator fun invoke(email: String, password: String, remember: Boolean): Resource<Unit, DataError.Network> {
       return loginRepository.login(email, password)
           .onSuccessAsync { handleSession(session = it, shouldRemember = remember) }
           .map { Unit }
    }

    private suspend fun handleSession(session: Session, shouldRemember: Boolean) {
        if (shouldRemember)
            cacheSession(session)

        sessionRepository.setSession(session)
    }

    private suspend fun cacheSession(session: Session) {
        with(cacheManager) {
            save(key = PreferenceKeys.TOKEN_KEY, value = session.token)
            save(key = PreferenceKeys.EMAIL_KEY, value = session.email)
        }
    }
}