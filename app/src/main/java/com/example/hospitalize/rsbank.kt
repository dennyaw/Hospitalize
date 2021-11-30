package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.app.Activity
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast




class rsbank : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rsbank)

        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<RumahSakitModelClass> = databaseHandler.viewRS("Surabaya")
        val empId = Array<String>(emp.size){"15"}
        val empRS = Array<String>(emp.size){"null"}
        val empAlamat = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empId[index] = e.rumahSakitId.toString()
            empRS[index] = e.rumahSakit
            empAlamat[index] = e.alamat
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = rs_list_adapter(this,empId,empRS,empAlamat)

        val listView = findViewById<ListView>(R.id.rs_listview)
        listView.adapter = myListAdapter

//        listView.setOnItemClickListener { parent, view, position, id ->
//            val element = myListAdapter.getItemId() // The item that was clicked
//            val intent = Intent(this, formbank::class.java)
//            startActivity(intent)
//        }

        listView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val intent = Intent(this, formbank::class.java)
                startActivity(intent)
            }


        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
        }

//        val rs1 = findViewById<ImageButton>(R.id.rs1)
//        rs1.setOnClickListener {
//            val intent = Intent(this, formbank::class.java)
//            startActivity(intent)
//        }
//
//        val rs2 = findViewById<ImageButton>(R.id.rs2)
//        rs2.setOnClickListener {
//            val intent = Intent(this, formbank::class.java)
//            startActivity(intent)
//        }

    }
}