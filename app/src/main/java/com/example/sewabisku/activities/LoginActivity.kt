package com.example.sewabisku.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sewabisku.R
import com.example.sewabisku.databinding.ActivityLoginBinding
import com.example.sewabisku.main.MenuActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        supportActionBar?.hide()

        appCompatButtonLogin.setOnClickListener{
            val email = textInputEditTextEmail.text.toString()
            val password = textInputEditTextPassword.text.toString()
            if (email.isEmpty()||password.isEmpty()) {
                Toast.makeText(this, "Masukkan email atau password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email == "admin@admin.com"|| password == "admin") {
                val progressDialog = ProgressDialog(this,R.style.Theme_MaterialComponents_Light_Dialog)
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Loading . . .")
                progressDialog.show()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this,"Email atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}