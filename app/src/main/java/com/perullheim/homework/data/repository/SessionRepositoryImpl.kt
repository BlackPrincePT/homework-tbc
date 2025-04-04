package com.perullheim.homework.data.repository

import com.perullheim.homework.domain.model.Session
import com.perullheim.homework.domain.repository.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor() : SessionRepository {

    private var currentSession: Session? = null

    override fun getSession(): Session? = currentSession

    override fun setSession(session: Session) {
        currentSession = session
    }

    override fun clearSession() {
        currentSession = null
    }
}
