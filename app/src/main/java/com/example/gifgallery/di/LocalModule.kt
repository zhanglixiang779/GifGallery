package com.example.gifgallery.di

import android.content.Context
import androidx.room.Room
import com.example.gifgallery.data.local.GifDao
import com.example.gifgallery.data.local.GifDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GifDatabase {
        return Room.databaseBuilder(context, GifDatabase::class.java, "Gif Database").build()
    }

    @Provides
    @Singleton
    fun provideGifDao(database: GifDatabase): GifDao {
        return database.gifDao()
    }
}