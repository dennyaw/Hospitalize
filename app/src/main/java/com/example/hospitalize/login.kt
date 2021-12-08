package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.widget.TextView
import com.example.hospitalize.adapter.RsListAdapter
import com.example.hospitalize.database.DatabaseHandler
import com.example.hospitalize.model.RumahSakitModelClass


class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val databaseHandler = DatabaseHandler(this)
        val akun:Int = databaseHandler.checkAccount()
        if (akun >= 0) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("akun_id",akun);
            Log.d("CREATION", "Login logged id:$akun")
            startActivity(intent)
        } else {
            val uname = findViewById<EditText>(R.id.uname)
            val password = findViewById<EditText>(R.id.password)

            val login = findViewById<ImageButton>(R.id.loginButton)
            login.setOnClickListener {
                val status = databaseHandler.loginAccount(uname.text.toString(), password.text.toString())
                if (status) {
                    val intent = Intent(this, MainActivity::class.java)
                    val akun_id = databaseHandler.checkAccount()
                    intent.putExtra("akun_id",akun_id);
                    Toast.makeText(getApplicationContext(), "Sukses login", Toast.LENGTH_LONG).show();
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(getApplicationContext(), "Pastikan username dan password anda benar", Toast.LENGTH_LONG).show();
                }
            }

            val registerBtn = findViewById<Button>(R.id.registerButton)
            registerBtn.setOnClickListener {
                val intent = Intent(this, register::class.java)
                startActivity(intent)
            }
        }
    }
    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}