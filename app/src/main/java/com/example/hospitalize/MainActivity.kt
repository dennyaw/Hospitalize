package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val donor = findViewById<ImageButton>(R.id.donordarah)
        donor.setOnClickListener {
            val intent = Intent(this, donor_location::class.java)
            startActivity(intent)
        }

        val bank = findViewById<ImageButton>(R.id.bankdarah)
        bank.setOnClickListener {
            val intent = Intent(this, bank_location::class.java)
            startActivity(intent)
        }

    }
}