package com.skreep.subeeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.skreep.subeeapp.data.SubDatabase
import com.skreep.subeeapp.repository.SubRepository
import com.skreep.subeeapp.model.Subscription
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SubViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Subscription>>
    private val repository: SubRepository

    init {
        val subDao = SubDatabase.getDatabase(application).subDao()
        repository = SubRepository(subDao)
        readAllData = repository.readAllData
    }

    fun addSub(subscription: Subscription) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSub(subscription)
        }
    }

    fun updateSub(subscription: Subscription) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSub(subscription)
        }
    }

    fun deleteSub(subscription: Subscription) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSub(subscription)
        }

    }

}
