package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import com.example.hospitalize.database.DatabaseHandler

class akun : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun)
        val databaseHandler = DatabaseHandler(this)
        val akun_id: Int?
        val extras = intent.extras
        akun_id = extras?.getInt("akun_id")
        Log.d("CREATION", "Account logged id:$akun_id")

        val logoutBtn = findViewById<ImageButton>(R.id.logout)
        logoutBtn.setOnClickListener {
            databaseHandler.logoutAccount(akun_id!!)
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
        }
    }
}