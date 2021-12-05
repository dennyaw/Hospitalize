package com.example.hospitalize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.hospitalize.database.DatabaseHandler
import com.example.hospitalize.model.RumahSakitModelClass
import android.view.View
import android.widget.RadioGroup

class donor_form : AppCompatActivity() {

    private var radioButton: RadioGroup? = null

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

        val rg = findViewById<View>(R.id.goldar_radio) as RadioGroup
        var goldar = "A"
        var checked = false

        rg.setOnCheckedChangeListener { group, checkedId ->
            checked = true
            when (checkedId) {
                R.id.A -> { goldar = "A" }
                R.id.B -> { goldar = "B" }
                R.id.AB -> { goldar = "AB" }
                R.id.O -> { goldar = "O" }
            }
        }

        val submit = findViewById<ImageButton>(R.id.donorButton)
        submit.setOnClickListener {
            if(checked) {
                // Membuat komponen alert
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Konfirmasi")
                builder.setMessage("Apakah kamu yakin data yang dimasukkan sudah benar?")

                builder.setPositiveButton("YES") { dialog, which ->
                    databaseHandler.addGoldar(rs_id, goldar)
                    Toast.makeText(getApplicationContext(), "Sukses melakukan donor", Toast.LENGTH_LONG).show();
                }

                builder.setNegativeButton("NO") { dialog, which -> }

                builder.show()
            }
            else {
                Toast.makeText(getApplicationContext(), "Lengkapi data terlebih dahulu!", Toast.LENGTH_LONG).show();
            }

        }
        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
        }

    }
}