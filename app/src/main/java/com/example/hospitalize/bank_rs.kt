package com.example.hospitalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.hospitalize.adapter.RsViewAdapter
import com.example.hospitalize.database.RsEntity
import com.example.hospitalize.view_model.RsViewModel

import android.widget.TextView


class bank_rs : AppCompatActivity(), RsViewAdapter.RowClickListener {

    lateinit var recyclerViewAdapter: RsViewAdapter
    lateinit var viewModel: RsViewModel
    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_rs)

        listView = findViewById(R.id.rs_listview)

//        val rs_recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.rs_recyclerView)
//        rs_recyclerView.apply {
//            Log.d("CREATION", "RecyclerView executed")
//            layoutManager = LinearLayoutManager(this@bank_rs)
//            recyclerViewAdapter = RsViewAdapter(this@bank_rs)
//            adapter = RsViewAdapter(this@bank_rs)
////            val divider = DividerItemDecoration(applicationContext, VERTICAL)
////            addItemDecoration(divider)
//        }
//
//        viewModel = ViewModelProviders.of(this).get(RsViewModel::class.java)
//        viewModel.getAllRsObservers().observe(this, Observer {
//            recyclerViewAdapter.setListData(ArrayList(it))
//            recyclerViewAdapter.notifyDataSetChanged()
//        })

//        DATABASE LAMA
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<RumahSakitModelClass> = databaseHandler.viewRS("Surabaya")
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

        var list = mutableListOf<RsModel>()

        for(e in emp){
            list.add(RsModel(e.rumahSakitId.toString(), e.rumahSakit, e.alamat))
        }

        listView.adapter = RsListAdapter(this,R.layout.rs_list,list)
        Log.d("CREATION", "Bikin listview")

        listView.setOnItemClickListener{parent, view, position, id ->
            val idText = view.findViewById(R.id.rs_id) as TextView
            val rs_id = idText.text.toString()
//            Log.d("CREATION", rs_id)
            val intent = Intent(this, bank_detail::class.java)
            intent.putExtra("key",rs_id);
            startActivity(intent)

        }

        //creating custom ArrayAdapter
//        val myListAdapter = rs_list_adapter(this,empId,empRS,empAlamat)
//
//        val listView = findViewById<ListView>(R.id.rs_listview)
//        listView.adapter = myListAdapter

//        listView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
//            myListAdapter.getPosition() // here you will get the clicked item from
//            //your imagelist and you can check by getting a title  by using this
//            val title: String = imageList.get(position).getTitle()
//            if (title == "you title to match") {
//                //do your action or you can get a particular position and click there
//            }
//        })

//        ArrayAdapter arrayAdapter = new ArrayAdapter

//        listView.setOnClickListener(new AdapterView<?> parent, View view, int position, long id) {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,long id)
//        }

//        listView.onItemClickListener =
//            OnItemClickListener { parent, view, position, id ->
//                val intent = Intent(this, bank_detail::class.java)
//                startActivity(intent)
//            }

        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
        }


    }

    override fun onRsClickListener(user: RsEntity) {
        TODO("Not yet implemented")
    }

    override fun onItemClickListener(user: RsEntity) {
        TODO("Not yet implemented")
    }
}