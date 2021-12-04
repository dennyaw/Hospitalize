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


class bank_rs : AppCompatActivity() {

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_rs)

        listView = findViewById(R.id.rs_listview)
        val rs_kota: String?
        val rs_provinsi: String?
        val extras = intent.extras
        rs_kota = extras?.getString("key")
        rs_provinsi = extras?.getString("province")

        findViewById<TextView>(R.id.lokasi).text = "$rs_kota, $rs_provinsi"

        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<RumahSakitModelClass> = databaseHandler.viewRS(rs_kota!!)

        listView.adapter = RsListAdapter(this,R.layout.rs_list,emp)
        Log.d("CREATION", "Bikin listview")

        listView.setOnItemClickListener{parent, view, position, id ->
            val idText = view.findViewById(R.id.rs_id) as TextView
            val rs_id = idText.text.toString()
//            Log.d("CREATION", rs_id)
            val intent = Intent(this, bank_detail::class.java)
            intent.putExtra("key",rs_id);
            startActivity(intent)

        }

        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
        }


    }
}