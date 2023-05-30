package com.example.sewabisku.bus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sewabisku.R
import kotlinx.android.synthetic.main.recyclervie_bus.view.*

class busadapter (val BusItemList:List<busdata>, val clickListener:(busdata) ->Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val  inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyclervie_bus,parent,false)
        return PartViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PartViewHolder).bind(BusItemList[position], clickListener)
    }

    override fun getItemCount() = BusItemList.size

    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: busdata, clickListener: (busdata) -> Unit) {
            itemView.tv_name.text = data.name
            itemView.image_bus.setImageResource(data.photo)
            itemView.tv_kursi.text = data.kursi
            itemView.setOnClickListener {clickListener(data)}
        }
    }
}