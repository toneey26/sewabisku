package com.example.sewabisku.history

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
import com.example.sewabisku.activities.convertLongToDateString
import com.example.sewabisku.data.sewa
import com.example.sewabisku.databinding.HistoryBinding
import com.example.sewabisku.sewa.sewabusViewModel
import kotlinx.android.synthetic.main.daftar_sewa.*
import kotlinx.android.synthetic.main.history.*

class HistoryActivity : AppCompatActivity() {
    companion object {
        const val HISTORY_REQUEST = 2
    }

    private lateinit var viewModel: sewabusViewModel
    private lateinit var binding: HistoryBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.history)

        rv_history.layoutManager = LinearLayoutManager(this)
        rv_history.setHasFixedSize(true)
        val adapter = Historyadapter()
        rv_history.adapter = adapter
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
        adapter.setOnItemClickListener(object : Historyadapter.OnItemClickListener {
            override fun onItemClick(sewa: sewa) {
                val intent = Intent(baseContext, DetailHistory::class.java)
                intent.putExtra(DetailHistory.EXTRA_ID, sewa.id_sewa)
                intent.putExtra(DetailHistory.EXTRA_NAMAP, sewa.namapelanggans)
                intent.putExtra(DetailHistory.EXTRA_PAKET, sewa.paketsewa)
                intent.putExtra(DetailHistory.EXTRA_TANGGALS, sewa.tanggalsewa)
                intent.putExtra(DetailHistory.EXTRA_BUS, sewa.busnama)
                intent.putExtra(DetailHistory.EXTRA_HARGA, sewa.hargas)
                intent.putExtra(DetailHistory.EXTRA_TANGGALP, convertLongToDateString(sewa.tanggalpesan))
                startActivityForResult(intent, HISTORY_REQUEST)
            }
        })
    }
}