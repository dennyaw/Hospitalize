package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import com.example.hospitalize.databinding.ActivityBankLocationBinding
import android.widget.Spinner




class bank_location : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_location)

        val binding = ActivityBankLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }

        val mySpinner = findViewById<View>(R.id.kota) as Spinner
        val mySpinner_parent = findViewById<View>(R.id.provinsi) as Spinner

        val cari = findViewById<ImageButton>(R.id.cari)
        cari.setOnClickListener {
            val spinnerText = mySpinner.selectedItem.toString()
            val spinnerParentText = mySpinner_parent.selectedItem.toString()
            val intent = Intent(this, bank_rs::class.java)
            intent.putExtra("key", spinnerText);
            intent.putExtra("province", spinnerParentText);
            startActivity(intent)
        }
    }
}