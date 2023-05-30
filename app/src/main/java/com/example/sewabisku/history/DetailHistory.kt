package com.example.sewabisku.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sewabisku.R
import kotlinx.android.synthetic.main.riwayat_detail.*

class DetailHistory : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.sewabisku.history.EXTRA_ID"
        const val EXTRA_NAMAP = "com.example.sewabisku.history.EXTRA_NAMAP"
        const val EXTRA_PAKET = "com.example.sewabisku.history.EXTRA_PAKET"
        const val EXTRA_TANGGALS = "com.example.sewabisku.history.EXTRA_TANGGALS"
        const val EXTRA_BUS = "com.example.sewabisku.history.EXTRA_BUS"
        const val EXTRA_HARGA = "com.example.sewabisku.history.EXTRA_HARGA"
        const val EXTRA_TANGGALP = "com.example.sewabisku.history.EXTRA_TANGGALP"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_delete_24)
        setContentView(R.layout.riwayat_detail)

        if (intent.hasExtra(EXTRA_ID)) {

            // judul pada halaman edit
            title = "Detail Riwayat"
            // menampilkan judul ketika ditekan judul yang direcyclerview
            p_pelanggan.setText(intent.getStringExtra(EXTRA_NAMAP))
            // menampikan telepon ketika judul di recyclerview ditekan
            p_tanggalp.setText(intent.getStringExtra(EXTRA_TANGGALS))
            // menampikan number_picker ketika judul di recyclerview ditekan
            p_busp.setText(intent.getStringExtra(EXTRA_BUS))
            p_harga.setText(intent.getStringExtra(EXTRA_HARGA))
            p_tanggals.setText(intent.getStringExtra(EXTRA_TANGGALP))
            p_paket.setText(intent.getStringExtra(EXTRA_PAKET))
        } else {
            // judul tambah catatan
            title = "Form Sewa Bus"
        }
    }
}