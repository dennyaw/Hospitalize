package com.example.hospitalize.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.hospitalize.database.RoomAppDb
import com.example.hospitalize.database.RsDao
import com.example.hospitalize.database.RsEntity

class RsViewModel(app: Application): AndroidViewModel(app) {
    lateinit var allRs : MutableLiveData<List<RsEntity>>

    init {
        allRs = MutableLiveData()
    }

    fun getAllRsObservers(): MutableLiveData<List<RsEntity>> {
        return allRs
    }

    fun getAllRs() {
        val rsDao = RoomAppDb.getAppDatabase((getApplication()))?.rsDao()
        val list = rsDao?.getAllRsInfo()

        allRs.postValue(list)
    }

    fun updateRs(entity: RsEntity) {
        val rsDao = RoomAppDb.getAppDatabase(getApplication())?.rsDao()
        rsDao?.updateRs(entity)
        getAllRs()
    }
}