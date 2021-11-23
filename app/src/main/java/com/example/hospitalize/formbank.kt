package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.DialogInterface
import android.widget.*
import androidx.appcompat.app.AlertDialog
//import kotlinx.android.synthetic.main.activity_main.*

class formbank : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formbank)

        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<GoldarModelClass> = databaseHandler.viewGoldar(1)
        val empGoldar = Array<String>(emp.size){"15"}
        val empGoldarStok = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empGoldar[index] = e.goldar
            empGoldarStok[index] = e.goldarStok.toString()
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = goldarlist_adapter(this,empGoldar,empGoldarStok)

        val listView = findViewById<ListView>(R.id.goldar_listview)
        listView.adapter = myListAdapter


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