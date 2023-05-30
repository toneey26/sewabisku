package com.example.sewabisku.pelanggan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sewabisku.R
import com.example.sewabisku.data.pelanggan
import kotlinx.android.synthetic.main.recyclerviewkon.view.*

class pelangganadapter : ListAdapter<pelanggan, pelangganadapter.pelangganHolder>(DIFF_CALLBACK){
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<pelanggan>() {
                override fun areItemsTheSame(oldItem: pelanggan, newItem: pelanggan): Boolean {
                    return oldItem.id_pelanggan == newItem.id_pelanggan
                }
                override fun areContentsTheSame(oldItem: pelanggan, newItem: pelanggan): Boolean {
                    return oldItem.namapelanggan == newItem.namapelanggan
                            && oldItem.emailpelanggan == newItem.emailpelanggan
                            && oldItem.teleponpelanggan == newItem.teleponpelanggan
                            && oldItem.alamatpelanggan == newItem.alamatpelanggan
                }
            }
    }
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pelangganHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerviewkon, parent, false)
        return pelangganHolder(itemView)
    }

    override fun onBindViewHolder(holder: pelangganHolder, position: Int) {
        val currentproduk: pelanggan = getItem(position)
        holder.textViewnama.text = currentproduk.namapelanggan
        holder.textViewemail.text = currentproduk.emailpelanggan }
    fun getpelangganAt(position: Int): pelanggan {
        return getItem(position)
    }
    inner class pelangganHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewnama: TextView = itemView.tv_name
        var textViewemail: TextView = itemView.tv_email
    }
    interface OnItemClickListener {
        fun onItemClick(pelanggan: pelanggan)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}