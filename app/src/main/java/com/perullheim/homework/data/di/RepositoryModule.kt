package com.perullheim.homework.data.di

import com.perullheim.homework.data.api.ResortService
import com.perullheim.homework.data.repositories.ResortsRepositoryImpl
import com.perullheim.homework.domain.repositories.ResortsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideResortsRepository(resortService: ResortService): ResortsRepository {
        return ResortsRepositoryImpl(resortService)
    }
}