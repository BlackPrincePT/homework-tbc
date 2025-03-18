package com.perullheim.homework.di

import com.perullheim.homework.data.repository.EquipmentRepositoryImpl
import com.perullheim.homework.domain.repository.EquipmentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideEquipmentRepository(repository: EquipmentRepositoryImpl): EquipmentRepository
}