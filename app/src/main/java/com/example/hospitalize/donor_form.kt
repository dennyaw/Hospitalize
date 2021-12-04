package com.example.hospitalize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.hospitalize.adapter.GoldarListAdapter
import com.example.hospitalize.database.DatabaseHandler
import com.example.hospitalize.model.RumahSakitModelClass

class donor_form : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_form)

        val rs_id: String?
        val extras = intent.extras
        rs_id = extras?.getString("key")

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val empRs: List<RumahSakitModelClass> = databaseHandler.viewRSDetail(rs_id!!)
        // INFO RS
        var RsName = "x"
        var RsCity = "x"

        for(e in empRs){
            RsName = e.rumahSakit
            RsCity = e.region
        }

        findViewById<TextView>(R.id.rs_name).text = RsName
        findViewById<TextView>(R.id.lokasi).text = RsCity

        val submit = findViewById<ImageButton>(R.id.donorButton)
        submit.setOnClickListener {
            // Membuat komponen alert
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi")
            builder.setMessage("Apakah kamu yakin data yang dimasukkan sudah benar?")

            builder.setPositiveButton("YES") { dialog, which ->
//                databaseHandler.updateGoldar(goldar_id, goldar_stok)
                Toast.makeText(getApplicationContext(), "Sukses melakukan donor", Toast.LENGTH_LONG).show();
            }

            builder.setNegativeButton("NO") { dialog, which -> }

            builder.show()
        }
        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
        }

    }
}