package com.example.sewabisku.sewa

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sewabisku.data.sewa

class sewabusViewModel (application: Application) : AndroidViewModel(application){
    private var repository: sewabusrepository = sewabusrepository(application)
    private var allsewabus: LiveData<List<sewa>> = repository.getSewa()
    fun insert(sewa: sewa) {
        repository.insert(sewa)
    }
    fun update(sewa: sewa) {
        repository.update(sewa)
    }
    fun delete(sewa: sewa) {
        repository.delete(sewa)
    }
    fun getSewa(): LiveData<List<sewa>> {
        return allsewabus
    }
}