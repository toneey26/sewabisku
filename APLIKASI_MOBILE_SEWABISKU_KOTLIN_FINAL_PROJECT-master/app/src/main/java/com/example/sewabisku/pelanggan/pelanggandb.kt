package com.example.sewabisku.pelanggan

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sewabisku.data.PelangganDAO
import com.example.sewabisku.data.pelanggan

@Database(entities = [pelanggan::class],version = 1)
abstract class pelanggandb : RoomDatabase(){
    abstract fun PelangganDAO():PelangganDAO
    companion object {
        private var instance: pelanggandb? = null
        fun getInstance(context: Context): pelanggandb? {
            if (instance == null) {
                synchronized(pelanggandb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        pelanggandb::class.java, "pelanggan_database")
                        .fallbackToDestructiveMigration() .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }
        private val roomCallback = object :
            RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            } } }
    class PopulateDbAsyncTask(db: pelanggandb?) : AsyncTask<Unit, Unit, Unit>() {
        private val Produkdao = db?.PelangganDAO()
        override fun doInBackground(vararg p0: Unit?) {
        }
    }
}
