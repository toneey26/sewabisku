package com.example.sewabisku.pelanggan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.sewabisku.R
import kotlinx.android.synthetic.main.tambah_konsumen.*

class tambaheditpelangganfragment : AppCompatActivity(){

    companion object {
        const val EXTRA_ID = "com.example.sewabisku.pelanggan.EXTRA_ID"
        const val EXTRA_NAMA = "com.example.sewabisku.pelanggan.EXTRA_NAMA"
        const val EXTRA_EMAIL = "com.example.sewabisku.pelanggan.EXTRA_EMAIL"
        const val EXTRA_TELEPON = "com.example.sewabisku.pelanggan.EXTRA_TELEPON"
        const val EXTRA_ALAMAT = "com.example.sewabisku.pelanggan.EXTRA_ALAMAT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_delete_24)
        setContentView(R.layout.tambah_konsumen)

        if (intent.hasExtra(EXTRA_ID)) {
            // judul pada halaman edit
            title = "Edit Pelanggan "
            // menampilkan judul ketika ditekan judul yang direcyclerview
            nama_p_edit.setText(intent.getStringExtra(EXTRA_NAMA))
            // menampilkan deskripsi ketika judul di recyclerview ditekan
            email_p_edit.setText(intent.getStringExtra(EXTRA_EMAIL))
            // menampikan telepon ketika judul di recyclerview ditekan
            telepon_p_edit.setText(intent.getStringExtra(EXTRA_TELEPON))
            // menampikan number_picker ketika judul di recyclerview ditekan
            alamat_p_edit.setText(intent.getStringExtra(EXTRA_ALAMAT))
        } else {
            // judul tambah catatan
            title = "Form Pelanggan"
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

        if (nama_p_edit.text.toString().trim().isEmpty()) {
            input_nama_p.error = getString(R.string.null_field,"Nama")
            input_nama_p.requestFocus()
            return
        } else if(email_p_edit.text.toString().trim().isEmpty()) {
            email_p_edit.error = getString(R.string.null_field,"Email")
            email_p_edit.requestFocus()
            return
        } else if (telepon_p_edit.text.toString().trim().isEmpty()) {
            telepon_p_edit.error = getString(R.string.null_field,"Nama")
            telepon_p_edit.requestFocus()
            return
        } else if (alamat_p_edit.text.toString().trim().isEmpty()) {
            alamat_p_edit.error = getString(R.string.null_field,"Nama")
            alamat_p_edit.requestFocus()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_NAMA, nama_p_edit.text.toString())
            putExtra(EXTRA_EMAIL, email_p_edit.text.toString())
            putExtra(EXTRA_TELEPON, telepon_p_edit.text.toString())
            putExtra(EXTRA_ALAMAT, alamat_p_edit.text.toString())
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}