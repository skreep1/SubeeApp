package com.skreep.subeeapp.repository

import androidx.lifecycle.LiveData
import com.skreep.subeeapp.data.SubDao
import com.skreep.subeeapp.model.Subscription

class SubRepository(private val subDao: SubDao) {

    val readAllData: LiveData<List<Subscription>> = subDao.readAllData()

    suspend fun addSub(subscription: Subscription) {
        subDao.addSub(subscription)
    }

    suspend fun updateSub(subscription: Subscription) {
        subDao.updateSub(subscription)
    }

    suspend fun deleteSub(subscription: Subscription){
        subDao.deleteSub(subscription)
    }

}