package com.perullheim.homework.data.di

import com.perullheim.homework.data.repository.abstraction.AddressRepository
import com.perullheim.homework.data.repository.impl.AddressRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAddressRepository(addressRepositoryImpl: AddressRepositoryImpl): AddressRepository
}