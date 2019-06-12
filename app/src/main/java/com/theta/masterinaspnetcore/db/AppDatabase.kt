package com.theta.masterinaspnetcore.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.theta.masterinaspnetcore.model.Category
import com.theta.masterinaspnetcore.model.Post

@Database(entities = [Category::class, Post::class],version = 1)
@TypeConverters(TitleConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao() : CategoryDao
    abstract fun postDao() : PostDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null
        private const val DATABASE_NAME = "DevUtilsLearnDotNet.db"

        fun getDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context,
                    AppDatabase::class.java, DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}