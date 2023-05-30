package com.example.sewabisku.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PelangganDAO {

    @Insert
    fun insert(pelanggan: pelanggan)

    @Update
    fun update(pelanggan: pelanggan)

    @Query("SELECT * FROM tabel_pelanggan")
    fun getPelanggan(): LiveData<List<pelanggan>>

    @Delete
    fun delete(pelanggan: pelanggan)

    @Query("DELETE FROM tabel_pelanggan")
    fun clear()
}

@Dao
interface SewabusDAO {

    @Insert
    fun insert(sewa: sewa)

    @Update
    fun update(sewa: sewa)

    @Query("SELECT * FROM tabel_sewa")
    fun getSewa(): LiveData<List<sewa>>

    @Delete
    fun delete(sewa: sewa)

    @Query("DELETE FROM tabel_sewa")
    fun clear()
}