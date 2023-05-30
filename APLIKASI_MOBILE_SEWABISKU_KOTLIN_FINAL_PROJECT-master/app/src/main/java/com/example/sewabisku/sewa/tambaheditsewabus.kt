package com.example.sewabisku.sewa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.sewabisku.R
import com.example.sewabisku.activities.rupiah
import kotlinx.android.synthetic.main.pemesanan.*

class tambaheditsewabus : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.sewabisku.sewa.EXTRA_ID"
        const val EXTRA_NAMAP = "com.example.sewabisku.sewa.EXTRA_NAMAP"
        const val EXTRA_PAKET = "com.example.sewabisku.sewa.EXTRA_PAKET"
        const val EXTRA_TANGGALS = "com.example.sewabisku.sewa.EXTRA_TANGGALS"
        const val EXTRA_BUS = "com.example.sewabisku.sewa.EXTRA_BUS"
        const val EXTRA_HARGA = "com.example.sewabisku.sewa.EXTRA_HARGA"
        const val EXTRA_TANGGALP = "com.example.sewabisku.sewa.EXTRA_TANGGALP"
    }

    private var paket = ""
    private var harga = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_delete_24)
        setContentView(R.layout.pemesanan)

        if (intent.hasExtra(EXTRA_ID)) {

            when(intent.getStringExtra(EXTRA_PAKET)) {
                "3 Hari" -> paket1.isChecked = true
                "1 Minggu"-> paket2.isChecked = true
            }
            // judul pada halaman edit
            title = "Edit Pelanggan "
            // menampilkan judul ketika ditekan judul yang direcyclerview
            nama_s_edit.setText(intent.getStringExtra(EXTRA_NAMAP))
            // menampikan telepon ketika judul di recyclerview ditekan
            tgl_sewa.setText(intent.getStringExtra(EXTRA_TANGGALS))
            // menampikan number_picker ketika judul di recyclerview ditekan
            bus_s_edit.setText(intent.getStringExtra(EXTRA_BUS))
            harga_sewa.setText(intent.getStringExtra(EXTRA_HARGA))
        } else {
            // judul tambah catatan
            title = "Form Sewa Bus"
        }

//        pilibus()

        cekhargasewa(harga_sewa)
    }

    private fun cekhargasewa(hargasewa: EditText) {
        r_input_s.setOnCheckedChangeListener{ _, _ ->
            when {
                paket1.isChecked -> {
                    harga = 30000.0
                    paket = "3 Hari"
                    hargasewa.setText(rupiah(harga))
                }
                paket2.isChecked -> {
                    harga = 35000.0
                    paket = "1 Minggu"
                    hargasewa.setText(rupiah(harga))
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // id save_note ditekan
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // eksekusi saveNote
    private fun saveNote() {

        if (nama_s_edit.text.toString().trim().isEmpty()) {
            input_nama_s.error = getString(R.string.null_field,"Nama")
            input_nama_s.requestFocus()
            return
        } else if(tgl_sewa.text.toString().trim().isEmpty()) {
            input_tgl_s.error = getString(R.string.null_field,"Tanggal")
            input_tgl_s.requestFocus()
            return
        } else if (bus_s_edit.text.toString().trim().isEmpty()) {
            input_bus_s.error = getString(R.string.null_field,"Input Bus")
            input_bus_s.requestFocus()
            return
        } else if (harga_sewa.text.toString().trim().isEmpty()) {
            input_harga_s.error = getString(R.string.null_field,"Pilih Paket")
            input_harga_s.requestFocus()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_NAMAP, nama_s_edit.text.toString())
            putExtra(EXTRA_PAKET, paket)
            putExtra(EXTRA_TANGGALS, tgl_sewa.text.toString())
            putExtra(EXTRA_BUS, bus_s_edit.text.toString())
            putExtra(EXTRA_HARGA, harga_sewa.text.toString())
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}