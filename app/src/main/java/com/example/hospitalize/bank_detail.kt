package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import android.widget.*

//import kotlinx.android.synthetic.main.activity_main.*

class bank_detail : AppCompatActivity() {

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_detail)

        listView = findViewById(R.id.goldar_listview)

        val rs_id: String?
        val extras = intent.extras
//        if (extras != null) {
        rs_id = extras?.getString("key")
        Log.d("CREATION", rs_id!!)

        //The key argument here must match that used in the other activity
//        }

        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<GoldarModelClass> = databaseHandler.viewGoldar(rs_id.toInt())
        val empRs: List<RumahSakitModelClass> = databaseHandler.viewRSDetail(rs_id)
//        val empGoldar = Array<String>(emp.size){"15"}
//        val empGoldarStok = Array<String>(emp.size){"null"}
//        var index = 0
//        for(e in emp){
//            empGoldar[index] = e.goldar
//            empGoldarStok[index] = e.goldarStok.toString()
//            index++
//        }

        var list = mutableListOf<GoldarModel>()

        for(e in emp){
            list.add(GoldarModel(e.sumberId.toString(), e.goldar, e.goldarStok.toString()))
        }
        var RsName = "x"
        var RsAddress = "x"
        var RsPhone = "x"
        var RsX = "x"
        var RsY = "x"

        for(e in empRs){
            RsName = e.rumahSakit
            RsAddress = e.alamat
            RsPhone = e.telepon
            RsX = e.sumbuX
            RsY = e.sumbuY
        }

        findViewById<TextView>(R.id.rsNameText).text = RsName
        findViewById<TextView>(R.id.notelp).text = RsPhone
        findViewById<TextView>(R.id.alamat).text = RsAddress

        listView.adapter = GoldarListAdapter(this,R.layout.goldar_list,list)

        //creating custom ArrayAdapter
//        val myListAdapter = goldar_list_adapter(this,empGoldar,empGoldarStok)

//        val listView = findViewById<ListView>(R.id.goldar_listview)
//        listView.adapter = myListAdapter


        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
        }

        val maps = findViewById<ImageButton>(R.id.map)
        maps.setOnClickListener {
            val intent = Intent(this, bank_map::class.java)
            intent.putExtra("x",RsX);
            intent.putExtra("y",RsY);
            startActivity(intent)
        }
    }
}