package com.example.sewabisku.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    (tableName = "tabel_pelanggan")
data class pelanggan (
    var namapelanggan: String,
    var emailpelanggan: String,
    var teleponpelanggan: String,
    var alamatpelanggan: String
) {
    @PrimaryKey(autoGenerate = true)
    var id_pelanggan: Int=0
}

@Entity
    (tableName = "tabel_sewa")
data class sewa (
    var namapelanggans: String,
    var paketsewa: String,
    var tanggalsewa: String,
    var busnama: String,
    var hargas: String,
    var tanggalpesan: Long = System.currentTimeMillis()
) {
    @PrimaryKey(autoGenerate = true)
    var id_sewa: Int=0
}