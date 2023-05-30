package com.example.sewabisku.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.sewabisku.R
import com.example.sewabisku.activities.LoginActivity


class MainActivity : AppCompatActivity() {

    private val loadingTime = 900

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, loadingTime.toLong())
    }

}