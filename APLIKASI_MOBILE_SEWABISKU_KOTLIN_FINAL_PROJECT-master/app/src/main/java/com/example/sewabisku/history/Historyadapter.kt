package com.example.sewabisku.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sewabisku.R
import com.example.sewabisku.activities.convertLongToDateString
import com.example.sewabisku.data.sewa
import kotlinx.android.synthetic.main.recyclerview_history.view.*

class Historyadapter  : ListAdapter<sewa, Historyadapter.historyHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<sewa>() {
                override fun areItemsTheSame(oldItem: sewa, newItem: sewa): Boolean {
                    return oldItem.id_sewa == newItem.id_sewa
                }
                override fun areContentsTheSame(oldItem: sewa, newItem: sewa): Boolean {
                    return oldItem.namapelanggans == newItem.namapelanggans
                            && oldItem.paketsewa == newItem.paketsewa
                            && oldItem.tanggalsewa == newItem.tanggalsewa
                            && oldItem.busnama == newItem.busnama
                            && oldItem.hargas == newItem.hargas
                            && oldItem.tanggalpesan == newItem.tanggalpesan
                }
            }
    }
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): historyHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_history, parent, false)
        return historyHolder(itemView)
    }

    override fun onBindViewHolder(holder: historyHolder, position: Int) {
        val currentproduk: sewa = getItem(position)
        holder.tanggalpesanv.text = convertLongToDateString(currentproduk.tanggalpesan)
        holder.busnamav.text = currentproduk.busnama
    }
    fun getsewabusAt(position: Int): sewa {
        return getItem(position)
    }
    inner class historyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var tanggalpesanv: TextView = itemView.tanggal_history
        var busnamav: TextView = itemView.nama_history
    }
    interface OnItemClickListener {
        fun onItemClick(sewa: sewa)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}