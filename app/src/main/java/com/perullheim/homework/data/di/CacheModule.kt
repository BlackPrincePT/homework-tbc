package com.perullheim.homework.data.di

import android.content.Context
import androidx.room.Room
import com.perullheim.homework.data.cache.DatabaseManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DatabaseManager {
        return Room.databaseBuilder(context, DatabaseManager::class.java, "myapp.db")
            .build()
    }
}