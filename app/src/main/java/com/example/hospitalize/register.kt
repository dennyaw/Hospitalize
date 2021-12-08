package com.example.hospitalize

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hospitalize.database.DatabaseHandler

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val databaseHandler = DatabaseHandler(this)
        val uname = findViewById<EditText>(R.id.uname)
        val password = findViewById<EditText>(R.id.password)
        val name = findViewById<EditText>(R.id.name)

        val register = findViewById<ImageButton>(R.id.registerButton)
        register.setOnClickListener {
            val status = databaseHandler.createAccount(uname.text.toString(), password.text.toString(), name.text.toString())
            Log.d("CREATION", "Create account uname,pass.name," + uname.text.toString() + password.text.toString() + name.text.toString())
            if (status) {
                Toast.makeText(getApplicationContext(), "Sukses mendaftar", Toast.LENGTH_LONG).show();
                finish()
            } else {
                Toast.makeText(getApplicationContext(), "Username sudah dipakai", Toast.LENGTH_LONG).show();
            }
        }

        val loginBtn = findViewById<Button>(R.id.loginButton)
        loginBtn.setOnClickListener {
            finish()
        }
    }
}