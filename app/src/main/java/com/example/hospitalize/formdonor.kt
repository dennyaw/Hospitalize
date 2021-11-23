package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class formdonor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formdonor)

        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
//            val intent = Intent(this, rsdonor::class.java)
//            startActivity(intent)
        }

    }
}