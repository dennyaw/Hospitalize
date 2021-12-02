package com.example.hospitalize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalize.adapter.RsViewAdapter
import com.example.hospitalize.database.RsEntity
import com.example.hospitalize.view_model.RsViewModel


class bank_rs : AppCompatActivity(), RsViewAdapter.RowClickListener {

    lateinit var recyclerViewAdapter: RsViewAdapter
    lateinit var viewModel: RsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_rs)

        val rs_recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.rs_recyclerView)
        rs_recyclerView.apply {
            Log.d("CREATION", "RecyclerView executed")
            layoutManager = LinearLayoutManager(this@bank_rs)
            recyclerViewAdapter = RsViewAdapter(this@bank_rs)
            adapter = RsViewAdapter(this@bank_rs)
//            val divider = DividerItemDecoration(applicationContext, VERTICAL)
//            addItemDecoration(divider)
        }

        viewModel = ViewModelProviders.of(this).get(RsViewModel::class.java)
        viewModel.getAllRsObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

//        DATABASE LAMA
//        //creating the instance of DatabaseHandler class
//        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
//        //calling the viewEmployee method of DatabaseHandler class to read the records
//        val emp: List<RumahSakitModelClass> = databaseHandler.viewRS("Surabaya")
//        val empId = Array<String>(emp.size){"15"}
//        val empRS = Array<String>(emp.size){"null"}
//        val empAlamat = Array<String>(emp.size){"null"}
//        var index = 0
//        for(e in emp){
//            empId[index] = e.rumahSakitId.toString()
//            empRS[index] = e.rumahSakit
//            empAlamat[index] = e.alamat
//            index++
//        }
//        //creating custom ArrayAdapter
//        val myListAdapter = rs_list_adapter(this,empId,empRS,empAlamat)
//
//        val listView = findViewById<ListView>(R.id.rs_listview)
//        listView.adapter = myListAdapter
//
//        listView.onItemClickListener =
//            OnItemClickListener { parent, view, position, id ->
//                val intent = Intent(this, bank_detail::class.java)
//                startActivity(intent)
//            }

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

    override fun onRsClickListener(user: RsEntity) {
        TODO("Not yet implemented")
    }

    override fun onItemClickListener(user: RsEntity) {
        TODO("Not yet implemented")
    }
}