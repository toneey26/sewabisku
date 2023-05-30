package com.example.sewabisku.sewa

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sewabisku.data.SewabusDAO
import com.example.sewabisku.data.sewa

@Database(entities = [sewa::class], version = 1)
abstract class sewabusdb : RoomDatabase() {
    abstract fun SewabusDAO(): SewabusDAO
    companion object {
        private var instance: sewabusdb? = null
        fun getInstance(context: Context): sewabusdb? {
            if (instance == null) {
                synchronized(sewabusdb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        sewabusdb::class.java, "sewa_database"  )
                        .fallbackToDestructiveMigration() .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }
        fun destroyInstance() {
            instance = null
        }
        private val roomCallback = object :
            RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            } } }
    class PopulateDbAsyncTask(db: sewabusdb?) : AsyncTask<Unit, Unit, Unit>() {
        private val Produkdao = db?.SewabusDAO()
        override fun doInBackground(vararg p0: Unit?) {
        }
    }
}