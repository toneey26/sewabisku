package com.example.sewabisku.sewa

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
import com.example.sewabisku.data.sewa
import com.example.sewabisku.databinding.DaftarSewaBinding
import kotlinx.android.synthetic.main.daftar_sewa.*

class sewabusfragment : AppCompatActivity() {

    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }

    private lateinit var viewModel: sewabusViewModel
    private lateinit var binding: DaftarSewaBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.daftar_sewa)
        buttontambahsewa.setOnClickListener {
            startActivityForResult(
                Intent(this, tambaheditsewabus::class.java),
                ADD_NOTE_REQUEST
            )
        }

        rv_sewa.layoutManager = LinearLayoutManager(this)
        rv_sewa.setHasFixedSize(true)
        val adapter = sewabusadapter()
        rv_sewa.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(sewabusViewModel::class.java)
        viewModel.getSewa().observe(this, Observer <List<sewa>>{
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
                            viewModel.delete(adapter.getsewabusAt(viewHolder.adapterPosition))
                            Toast.makeText(baseContext, "Produk dihapus!", Toast.LENGTH_SHORT).show()
                        })
                        .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                            Toast.makeText(baseContext, "Produk Tidak Terhapus", Toast.LENGTH_LONG).show()
                            adapter.notifyItemChanged(viewHolder.adapterPosition)
                        })
                        .show()
                }
            }
        ).attachToRecyclerView(rv_sewa)
        // klik adapter
        adapter.setOnItemClickListener(object : sewabusadapter.OnItemClickListener {
            override fun onItemClick(sewa: sewa) {
                val intent = Intent(baseContext, tambaheditsewabus::class.java)
                intent.putExtra(tambaheditsewabus.EXTRA_ID, sewa.id_sewa)
                intent.putExtra(tambaheditsewabus.EXTRA_NAMAP, sewa.namapelanggans)
                intent.putExtra(tambaheditsewabus.EXTRA_PAKET, sewa.paketsewa)
                intent.putExtra(tambaheditsewabus.EXTRA_TANGGALS, sewa.tanggalsewa)
                intent.putExtra(tambaheditsewabus.EXTRA_BUS, sewa.busnama)
                intent.putExtra(tambaheditsewabus.EXTRA_HARGA, sewa.hargas)
                intent.putExtra(tambaheditsewabus.EXTRA_TANGGALP, sewa.tanggalpesan)
                startActivityForResult(intent, EDIT_NOTE_REQUEST)
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // eksekusi tambah
        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val newSewa = sewa(
                data!!.getStringExtra(tambaheditsewabus.EXTRA_NAMAP),
                data.getStringExtra(tambaheditsewabus.EXTRA_PAKET),
                data.getStringExtra(tambaheditsewabus.EXTRA_TANGGALS),
                data.getStringExtra(tambaheditsewabus.EXTRA_BUS),
                data.getStringExtra(tambaheditsewabus.EXTRA_HARGA)
            )
            viewModel.insert(newSewa)
            Toast.makeText(this, "Pemesanan berhasil ditambah", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(tambaheditsewabus.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "Pembaharuan gagal!",
                    Toast.LENGTH_SHORT).show()
            }
            val updateSewa = sewa(
                data!!.getStringExtra(tambaheditsewabus.EXTRA_NAMAP),
                data.getStringExtra(tambaheditsewabus.EXTRA_PAKET),
                data.getStringExtra(tambaheditsewabus.EXTRA_TANGGALS),
                data.getStringExtra(tambaheditsewabus.EXTRA_BUS),
                data.getStringExtra(tambaheditsewabus.EXTRA_HARGA)
            )
            updateSewa.id_sewa = data.getIntExtra(tambaheditsewabus.EXTRA_ID, -1)
            viewModel.update(updateSewa)
        } else {
            Toast.makeText(this, "Pemesanan belum berhasil ditambah",
                Toast.LENGTH_SHORT).show()
        }
    }
}