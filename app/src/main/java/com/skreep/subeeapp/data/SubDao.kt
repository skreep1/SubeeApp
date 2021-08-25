package com.skreep.subeeapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.skreep.subeeapp.model.Subscription

/** SubDao - интерфейс содержит методы для доступа к БД
 * Выполняются все запросы, которые мы хотим сделать в нашей БД * */

@Dao
interface SubDao {
        //вставка
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun addSub(subscription: Subscription)

        @Update
        suspend fun updateSub(subscription: Subscription)

        @Delete
        suspend fun deleteSub(subscription: Subscription)

        @Query("DELETE FROM subscription_table")
        suspend fun deleteAllSub()

        //запрос
        @Query("SELECT * FROM subscription_table ORDER BY id ASC")
        fun readAllData() : LiveData<List<Subscription>>
    }
