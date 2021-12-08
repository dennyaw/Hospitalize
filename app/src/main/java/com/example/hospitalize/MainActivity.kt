package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val akun_id: Int?
        val extras = intent.extras
        akun_id = extras?.getInt("akun_id")
        Log.d("CREATION", "MainActivity account logged id:$akun_id")

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

        val akunBtn = findViewById<ImageButton>(R.id.akun)
        akunBtn.setOnClickListener {
            val intent = Intent(this, akun::class.java)
            intent.putExtra("akun_id", akun_id);
            startActivity(intent)
        }

    }
    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}