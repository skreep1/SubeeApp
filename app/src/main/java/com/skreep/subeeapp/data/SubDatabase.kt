package com.skreep.subeeapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skreep.subeeapp.model.Subscription

/** Подключение к БД*/

@Database(entities = [Subscription::class], version = 1, exportSchema = false)
abstract class SubDatabase : RoomDatabase() {

    abstract fun subDao(): SubDao

    companion object {
        @Volatile
        private var INSTANCE: SubDatabase? = null


    fun getDatabase(context: Context): SubDatabase {
        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return tempInstance
        }
        synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                SubDatabase::class.java,
                "subscription_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
}
}