package com.perullheim.homework.data.di

import com.perullheim.homework.data.api.AuthService
import com.perullheim.homework.data.api.UsersService
import com.perullheim.homework.data.datastore.DataStoreManager
import com.perullheim.homework.data.repositories.AuthRepositoryImpl
import com.perullheim.homework.data.repositories.UserSessionRepositoryImpl
import com.perullheim.homework.data.repositories.UsersRepositoryImpl
import com.perullheim.homework.domain.repositories.AuthRepository
import com.perullheim.homework.domain.repositories.UserSessionRepository
import com.perullheim.homework.domain.repositories.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @Provides
    @Singleton
    fun provideUserSessionRepository(dataStoreManager: DataStoreManager): UserSessionRepository {
        return UserSessionRepositoryImpl(dataStoreManager)
    }

    @Provides
    fun provideUsersRepository(usersService: UsersService): UsersRepository {
        return UsersRepositoryImpl(usersService)
    }
}