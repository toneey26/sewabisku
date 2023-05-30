package com.example.sewabisku.sewa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sewabisku.R
import com.example.sewabisku.data.sewa
import kotlinx.android.synthetic.main.recyclerviewsewa.view.*


class sewabusadapter : ListAdapter<sewa, sewabusadapter.sewabusHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sewabusHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerviewsewa, parent, false)
        return sewabusHolder(itemView)
    }

    override fun onBindViewHolder(holder: sewabusHolder, position: Int) {
        val currentproduk: sewa = getItem(position)
        holder.textViewnama.text = currentproduk.namapelanggans
        holder.textViewemail.text = currentproduk.busnama
    }
    fun getsewabusAt(position: Int): sewa {
        return getItem(position)
    }
    inner class sewabusHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewnama: TextView = itemView.tv_tgls
        var textViewemail: TextView = itemView.nama_se
    }
    interface OnItemClickListener {
        fun onItemClick(sewa: sewa)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}