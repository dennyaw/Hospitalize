package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class formbank : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formbank)

        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
//            val intent = Intent(this, rsbank::class.java)
//            startActivity(intent)
        }

        val maps = findViewById<ImageButton>(R.id.map)
        maps.setOnClickListener {
            val intent = Intent(this, map::class.java)
            startActivity(intent)
        }
    }
}