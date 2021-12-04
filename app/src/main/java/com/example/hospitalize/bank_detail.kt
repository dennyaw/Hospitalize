package com.example.hospitalize

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.hospitalize.adapter.GoldarListAdapter
import com.example.hospitalize.database.DatabaseHandler
import com.example.hospitalize.model.GoldarModel
import com.example.hospitalize.model.RumahSakitModelClass


//import kotlinx.android.synthetic.main.activity_main.*

class bank_detail : AppCompatActivity() {

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_detail)

        listView = findViewById(R.id.goldar_listview)

        val rs_id: String?
        val extras = intent.extras
        rs_id = extras?.getString("key")
        Log.d("CREATION", rs_id!!)

        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        var emp: List<GoldarModel> = databaseHandler.viewGoldar(rs_id.toInt())
        val empRs: List<RumahSakitModelClass> = databaseHandler.viewRSDetail(rs_id)

        // INFO RS
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

        listView.adapter = GoldarListAdapter(this, R.layout.goldar_list, emp)

        listView.setOnItemClickListener{parent, view, position, id ->
            val idText = view.findViewById(R.id.goldar_id) as TextView
            val goldar_id = idText.text.toString()
            val stokText = view.findViewById(R.id.goldar_stok) as TextView
            val goldar_stok = stokText.text.toString()

            // Membuat komponen alert
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Ambil kantung darah")
            builder.setMessage("Apakah kamu yakin ingin mengambil kantung darah?")

            builder.setPositiveButton("YES") { dialog, which ->
                databaseHandler.updateGoldar(goldar_id, goldar_stok)
                // Load ulang db baru
                emp = databaseHandler.viewGoldar(rs_id.toInt())
                listView.adapter = GoldarListAdapter(this, R.layout.goldar_list, emp)
                Toast.makeText(getApplicationContext(), "Sukses mengambil kantung darah", Toast.LENGTH_LONG).show();
            }

            builder.setNegativeButton("NO") { dialog, which -> }

            builder.show()

        }

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
        val telp = findViewById<ImageButton>(R.id.telp)
        telp.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel: $RsPhone")
            startActivity(dialIntent)
        }
    }
}