package com.example.sewabisku.pelanggan

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sewabisku.R
import com.example.sewabisku.data.pelanggan
import com.example.sewabisku.databinding.DaftarKonsumenBinding
import kotlinx.android.synthetic.main.daftar_konsumen.*


class pelanggan_fragment : AppCompatActivity(){

    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }

    private lateinit var viewModel: pelangganViewModel
    private lateinit var binding: DaftarKonsumenBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.daftar_konsumen)
        buttontambahkonsumen.setOnClickListener {
            startActivityForResult(
                Intent(this, tambaheditpelangganfragment::class.java),
                ADD_NOTE_REQUEST
            )
        }

        rv_kon.layoutManager = LinearLayoutManager(this)
        rv_kon.setHasFixedSize(true)
        val adapter = pelangganadapter()
        rv_kon.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(pelangganViewModel::class.java)
        viewModel.getPelanggan().observe(this, Observer <List<pelanggan>>{
            adapter.submitList(it)
        })

        ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                    target: RecyclerView.ViewHolder): Boolean {
                    return false
                }
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    AlertDialog.Builder(viewHolder.itemView.getContext())
                        // Judul
                        .setTitle("Warning")
                        // Pesan yang di tamopilkan
                        .setMessage("Ingin Dihapus ?")
                        .setPositiveButton("Ya", DialogInterface.OnClickListener(){ dialogInterface, i ->
                            viewModel.delete(adapter.getpelangganAt(viewHolder.adapterPosition))
                            Toast.makeText(baseContext, "Pesanan dihapus", Toast.LENGTH_SHORT).show()
                        })
                        .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                            Toast.makeText(baseContext, "Pesanan tidak terhapus", Toast.LENGTH_LONG).show()
                            adapter.notifyItemChanged(viewHolder.adapterPosition)
                        })
                        .show()
                }
            }
        ).attachToRecyclerView(rv_kon)
        // klik adapter
        adapter.setOnItemClickListener(object : pelangganadapter.OnItemClickListener {
            override fun onItemClick(pelanggan: pelanggan) {
                val intent = Intent(baseContext, tambaheditpelangganfragment::class.java)
                intent.putExtra(tambaheditpelangganfragment.EXTRA_ID, pelanggan.id_pelanggan)
                intent.putExtra(tambaheditpelangganfragment.EXTRA_NAMA, pelanggan.namapelanggan)
                intent.putExtra(tambaheditpelangganfragment.EXTRA_EMAIL, pelanggan.emailpelanggan)
                intent.putExtra(tambaheditpelangganfragment.EXTRA_TELEPON, pelanggan.teleponpelanggan)
                intent.putExtra(tambaheditpelangganfragment.EXTRA_ALAMAT, pelanggan.alamatpelanggan)
                startActivityForResult(intent, EDIT_NOTE_REQUEST)
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // eksekusi tambah
        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val newPelanggan = pelanggan(
                data!!.getStringExtra(tambaheditpelangganfragment.EXTRA_NAMA),
                data.getStringExtra(tambaheditpelangganfragment.EXTRA_EMAIL),
                data.getStringExtra(tambaheditpelangganfragment.EXTRA_TELEPON),
                data.getStringExtra(tambaheditpelangganfragment.EXTRA_ALAMAT)
            )
            viewModel.insert(newPelanggan)
            Toast.makeText(this, "Pelanggan berhasil ditambah", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(tambaheditpelangganfragment.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "Pembaharuan gagal!",
                    Toast.LENGTH_SHORT).show()
            }
            val updatePelanggan = pelanggan(
                data!!.getStringExtra(tambaheditpelangganfragment.EXTRA_NAMA),
                data.getStringExtra(tambaheditpelangganfragment.EXTRA_EMAIL),
                data.getStringExtra(tambaheditpelangganfragment.EXTRA_TELEPON),
                data.getStringExtra(tambaheditpelangganfragment.EXTRA_ALAMAT)
            )
            updatePelanggan.id_pelanggan = data.getIntExtra(tambaheditpelangganfragment.EXTRA_ID, -1)
            viewModel.update(updatePelanggan)
        } else {
            Toast.makeText(this, "Pelanggan belum berhasil ditambah",
                Toast.LENGTH_SHORT).show()
        }
    }
}