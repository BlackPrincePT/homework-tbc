package com.perullheim.homework.data.di

import com.perullheim.homework.data.repository.AuthRepositoryImpl
import com.perullheim.homework.data.repository.UsersRepositoryImpl
import com.perullheim.homework.domain.repository.AuthRepository
import com.perullheim.homework.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun provideUsersRepository(usersRepository: UsersRepositoryImpl): UsersRepository
}