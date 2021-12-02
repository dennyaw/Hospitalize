package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

//import kotlinx.android.synthetic.main.activity_main.*

class bank_detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_detail)

//        //creating the instance of DatabaseHandler class
//        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
//        //calling the viewEmployee method of DatabaseHandler class to read the records
//        val emp: List<GoldarModelClass> = databaseHandler.viewGoldar(1)
//        val empGoldar = Array<String>(emp.size){"15"}
//        val empGoldarStok = Array<String>(emp.size){"null"}
//        var index = 0
//        for(e in emp){
//            empGoldar[index] = e.goldar
//            empGoldarStok[index] = e.goldarStok.toString()
//            index++
//        }
//        //creating custom ArrayAdapter
//        val myListAdapter = goldar_list_adapter(this,empGoldar,empGoldarStok)
//
//        val listView = findViewById<ListView>(R.id.goldar_listview)
//        listView.adapter = myListAdapter


        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
        }

        val maps = findViewById<ImageButton>(R.id.map)
        maps.setOnClickListener {
            val intent = Intent(this, bank_map::class.java)
            startActivity(intent)
        }
    }
}