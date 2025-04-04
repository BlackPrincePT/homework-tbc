package com.perullheim.homework.domain.repository

import com.perullheim.homework.domain.model.Session

interface SessionRepository {
    fun getSession(): Session?
    fun setSession(session: Session)
    fun clearSession()
}