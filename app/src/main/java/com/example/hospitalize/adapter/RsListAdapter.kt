package com.example.hospitalize.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.hospitalize.R
import com.example.hospitalize.model.RumahSakitModelClass

class RsListAdapter(var mCtx:Context , var resource:Int,var items:List<RumahSakitModelClass>)
    :ArrayAdapter<RumahSakitModelClass>( mCtx , resource , items ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        var idText: TextView = view.findViewById(R.id.rs_id)
        var name: TextView = view.findViewById(R.id.rs_name)
        var address: TextView = view.findViewById(R.id.rs_alamat)


        var person: RumahSakitModelClass = items[position]

        idText.text = person.rumahSakitId.toString()
        name.text = person.rumahSakit
        address.text = person.alamat


        return view
    }
}
