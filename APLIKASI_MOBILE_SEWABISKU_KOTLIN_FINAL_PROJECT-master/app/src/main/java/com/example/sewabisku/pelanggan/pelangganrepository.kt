package com.example.sewabisku.pelanggan

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.sewabisku.data.*

class pelangganrepository(application: Application) {
    private var pelangganDAO:PelangganDAO
    private var allpelanggan: LiveData<List<pelanggan>>
    init {
        val database: pelanggandb = pelanggandb.getInstance(
            application.applicationContext
        )!!
        pelangganDAO = database.PelangganDAO()
        allpelanggan = pelangganDAO.getPelanggan()

    }
    fun insert(pelanggan: pelanggan) {
        val insertpelangganAsyncTask= InsertpelangganAsyncTask(pelangganDAO).execute(pelanggan)
    }
    fun update(pelanggan: pelanggan) {
        val updatepelangganAsyncTask= UpdatepelangganAsyncTask(pelangganDAO).execute(pelanggan)
    }
    fun delete(pelanggan: pelanggan) {
        val deletepelangganAsyncTask= DeletepelangganAsyncTask(pelangganDAO).execute(pelanggan)
    }
    fun getPelanggan() : LiveData<List<pelanggan>>{
        return allpelanggan
    }

    companion object {
        private class InsertpelangganAsyncTask(pelangganDAO: PelangganDAO) : AsyncTask<pelanggan, Unit, Unit>() {
            val pelangganDAO = pelangganDAO
            override fun doInBackground(vararg p0: pelanggan?) {
                pelangganDAO.insert(p0[0]!!)
            }
        }
        private class UpdatepelangganAsyncTask(pelangganDAO: PelangganDAO) : AsyncTask<pelanggan, Unit, Unit>() {
            val pelangganDAO=pelangganDAO

            override fun doInBackground(vararg p0: pelanggan?) {
                pelangganDAO.update(p0[0]!!)
            }
        }
        private class DeletepelangganAsyncTask(pelangganDAO: PelangganDAO) : AsyncTask<pelanggan, Unit, Unit>() {
            val pelangganDAO = pelangganDAO

            override fun doInBackground(vararg p0: pelanggan?) {
                pelangganDAO.delete(p0[0]!!)
            }
        }
    }
}