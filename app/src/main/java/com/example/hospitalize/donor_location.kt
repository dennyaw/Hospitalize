package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.hospitalize.databinding.ActivityBankLocationBinding

class donor_location : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_location)

        val binding = ActivityBankLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<TextView>(R.id.BankDarah).text = "Donor Darah"

        val arrayList_parent = arrayListOf<String>("DIY", "Jawa Timur")
        val arrayAdapter_parent = ArrayAdapter(applicationContext, R.layout.spinner_text, arrayList_parent)
        binding.provinsi.adapter = arrayAdapter_parent

        val arrayList_diy = arrayListOf<String>("Yogyakarta")
        val arrayList_jatim = arrayListOf<String>("Surabaya")
        val arrayAdapter_child = ArrayAdapter(applicationContext, R.layout.spinner_text, arrayList_diy)
        binding.kota.adapter = arrayAdapter_child

        binding.provinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position == 0) {
                    val arrayAdapter_child = ArrayAdapter(applicationContext, R.layout.spinner_text, arrayList_diy)
                    binding.kota.adapter = arrayAdapter_child
                }
                if(position == 1) {
                    val arrayAdapter_child = ArrayAdapter(applicationContext, R.layout.spinner_text, arrayList_jatim)
                    binding.kota.adapter = arrayAdapter_child
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
        }

        val mySpinner = findViewById<View>(R.id.kota) as Spinner
        val mySpinner_parent = findViewById<View>(R.id.provinsi) as Spinner

        val cari = findViewById<ImageButton>(R.id.cari)
        cari.setOnClickListener {
            val spinnerText = mySpinner.selectedItem.toString()
            val spinnerParentText = mySpinner_parent.selectedItem.toString()
            val intent = Intent(this, donor_rs::class.java)
            intent.putExtra("city", spinnerText);
            intent.putExtra("province", spinnerParentText);
            startActivity(intent)
        }
    }
}