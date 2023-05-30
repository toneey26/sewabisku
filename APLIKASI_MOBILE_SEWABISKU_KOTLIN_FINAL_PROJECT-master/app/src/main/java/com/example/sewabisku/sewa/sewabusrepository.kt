package com.example.sewabisku.sewa

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.sewabisku.data.*

class sewabusrepository(application: Application){
    private var sewabusDAO:SewabusDAO
    private var allsewabus: LiveData<List<sewa>>
    init {
        val database: sewabusdb = sewabusdb.getInstance(
            application.applicationContext
        )!!
        sewabusDAO = database.SewabusDAO()
        allsewabus = sewabusDAO.getSewa()

    }
    fun insert(sewa: sewa) {
        val insertsewabusAsyncTask
                = InsertsewabusAsyncTask(sewabusDAO).execute(sewa)
    }
    fun update(sewa: sewa) {
        val updatesewabusAsyncTask
                = UpdatesewabusAsyncTask(sewabusDAO).execute(sewa)
    }
    fun delete(sewa: sewa) {
        val deletesewaAsyncTask
                = DeletesewaAsyncTask(sewabusDAO).execute(sewa)
    }
    fun getSewa() : LiveData<List<sewa>>{
        return allsewabus
    }

    companion object {
        private class InsertsewabusAsyncTask(sewabusDAO: SewabusDAO) : AsyncTask<sewa, Unit, Unit>() {
            val sewabusDAO = sewabusDAO
            override fun doInBackground(vararg p0: sewa?) {
                sewabusDAO.insert(p0[0]!!)
            }
        }
        private class UpdatesewabusAsyncTask(sewabusDAO: SewabusDAO) : AsyncTask<sewa, Unit, Unit>() {
            val sewabusDAO=sewabusDAO

            override fun doInBackground(vararg p0: sewa?) {
                sewabusDAO.update(p0[0]!!)
            }
        }
        private class DeletesewaAsyncTask(sewabusDAO: SewabusDAO) : AsyncTask<sewa, Unit, Unit>() {
            val sewabusDAO = sewabusDAO

            override fun doInBackground(vararg p0: sewa?) {
                sewabusDAO.delete(p0[0]!!)
            }
        }
    }
}