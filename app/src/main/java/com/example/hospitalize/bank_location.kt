package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class bank_location : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_location)

        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }

        val cari = findViewById<ImageButton>(R.id.cari)
        cari.setOnClickListener {
            val intent = Intent(this, bank_rs::class.java)
            startActivity(intent)
        }
    }
}