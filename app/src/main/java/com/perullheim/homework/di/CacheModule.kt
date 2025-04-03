package com.perullheim.homework.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.perullheim.homework.data.local.datastore.DataStoreManager
import com.perullheim.homework.data.local.room.RoomManager
import com.perullheim.homework.domain.cache.CacheManager
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
    fun provideDatastore(@ApplicationContext context: Context): CacheManager {
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): RoomManager {
        return Room
            .databaseBuilder(context, RoomManager::class.java, name = "homework.db")
            .build()
    }
}