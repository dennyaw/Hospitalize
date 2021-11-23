package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class rsbank : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rsbank)

        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
//            val intent = Intent(this, lokasibank::class.java)
//            startActivity(intent)
        }

        val rs1 = findViewById<ImageButton>(R.id.rs1)
        rs1.setOnClickListener {
            val intent = Intent(this, formbank::class.java)
            startActivity(intent)
        }

        val rs2 = findViewById<ImageButton>(R.id.rs2)
        rs2.setOnClickListener {
            val intent = Intent(this, formbank::class.java)
            startActivity(intent)
        }

    }
}