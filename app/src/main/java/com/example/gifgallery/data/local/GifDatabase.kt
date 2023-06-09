package com.example.gifgallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalGif::class], version = 1, exportSchema = false)
abstract class GifDatabase : RoomDatabase() {

    abstract fun gifDao(): GifDao
}