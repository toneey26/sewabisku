package com.example.sewabisku.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sewabisku.Contact_fragment
import com.example.sewabisku.R
import com.example.sewabisku.bus.busfragment
import com.example.sewabisku.databinding.HomeFragmentBinding
import com.example.sewabisku.history.HistoryActivity
import com.example.sewabisku.pelanggan.pelanggan_fragment
import com.example.sewabisku.sewa.sewabusfragment

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: HomeFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.home_fragment)
        supportActionBar?.hide()

        // click listener
        binding.information.setOnClickListener {
            startActivity(Intent(this, Contact_fragment::class.java))
        }

        binding.bus.setOnClickListener {
            startActivity(Intent(this, busfragment::class.java))
        }

        binding.buttonpelanggan.setOnClickListener {
            startActivity(Intent(this, pelanggan_fragment::class.java))
        }

        binding.penyewaan.setOnClickListener {
            startActivity(Intent(this, sewabusfragment::class.java))
        }
        binding.riwayat.setOnClickListener{
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save,menu)
        return super.onCreateOptionsMenu(menu)
    }

}