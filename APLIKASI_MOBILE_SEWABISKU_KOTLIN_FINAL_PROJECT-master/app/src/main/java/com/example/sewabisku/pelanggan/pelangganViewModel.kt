package com.example.sewabisku.pelanggan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sewabisku.data.pelanggan

class pelangganViewModel (application: Application) : AndroidViewModel(application){
    private var repository: pelangganrepository = pelangganrepository(application)
    private var allpelanggan: LiveData<List<pelanggan>> = repository.getPelanggan()
    fun insert(pelanggan: pelanggan) {
        repository.insert(pelanggan)
    }
    fun update(pelanggan: pelanggan) {
        repository.update(pelanggan)
    }
    fun delete(pelanggan: pelanggan) {
        repository.delete(pelanggan)
    }
    fun getPelanggan(): LiveData<List<pelanggan>> {
        return allpelanggan
    }
}