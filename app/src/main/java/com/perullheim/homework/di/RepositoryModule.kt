package com.perullheim.homework.di

import com.perullheim.homework.data.repository.LoginRepositoryImpl
import com.perullheim.homework.data.repository.RegisterRepositoryImpl
import com.perullheim.homework.data.repository.SessionRepositoryImpl
import com.perullheim.homework.data.repository.UserRepositoryImpl
import com.perullheim.homework.domain.repository.LoginRepository
import com.perullheim.homework.domain.repository.RegisterRepository
import com.perullheim.homework.domain.repository.SessionRepository
import com.perullheim.homework.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideLoginRepository(repositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun provideRegisterRepository(repositoryImpl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    abstract fun provideUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun provideSessionRepository(repositoryImpl: SessionRepositoryImpl): SessionRepository
}