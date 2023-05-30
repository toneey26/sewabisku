package com.example.sewabisku.bus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sewabisku.R
import kotlinx.android.synthetic.main.daftar_bus.*


class busfragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftar_bus)
        val busdataadapter = createBusData()
        rv_bus.layoutManager = LinearLayoutManager(this)
        rv_bus.setHasFixedSize(true)
        rv_bus.adapter = busadapter(busdataadapter, { phoneItem : busdata -> phoneItemClicked(phoneItem)})
    }
    private fun phoneItemClicked(busitem: busdata) {
        val showDetailActivityIntent = Intent(this, busdetail::class.java)
        showDetailActivityIntent.putExtra(Intent.EXTRA_TEXT, busitem.photo)
        showDetailActivityIntent.putExtra(Intent.EXTRA_SUBJECT, busitem.name)
        showDetailActivityIntent.putExtra(Intent.EXTRA_TEMPLATE, busitem.deskripsi)
        showDetailActivityIntent.putExtra(Intent.EXTRA_TITLE, busitem.kursi)
        showDetailActivityIntent.putExtra(Intent.EXTRA_COMPONENT_NAME, busitem.harga)
        startActivity(showDetailActivityIntent)
    }
    private fun createBusData() : List<busdata> {
        val partList = ArrayList<busdata>()
        partList.add(
            busdata(
                R.drawable.bus,
                "Bus A",
                "50",
                "Paket Komplit",
            "Rp9000.000")
        )
        partList.add(
            busdata(
                R.drawable.bus,
                "Bus B",
                "50",
                "Paket Komplit",
            "Rp9000.000")
        )
        partList.add(
            busdata(
                R.drawable.bus,
                "Bus C",
                "50",
                "Paket Komplit",
            "Rp9000.000"))
        return partList
    }

}