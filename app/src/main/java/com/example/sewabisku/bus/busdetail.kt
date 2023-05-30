package com.example.sewabisku.bus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sewabisku.R
import kotlinx.android.synthetic.main.bus_detail.*

class busdetail:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_detail)

        var intentThatStartedThisActivity = getIntent()

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            var partName = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_SUBJECT)
            var partPhoto = intentThatStartedThisActivity.getIntExtra(Intent.EXTRA_TEXT,0)
            var partKursi = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TITLE)
            var partDesk = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEMPLATE)
            var partHarga = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_COMPONENT_NAME)
            merk_bus.text = partName
            deskripsi_bus.text = partDesk
            kursi_bus.text = partKursi
            harga_bus.text = partHarga
            bus_photo.contentDescription = partName
            bus_photo.setImageResource(partPhoto)
        }
    }
}