package com.example.hospitalize.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.hospitalize.model.GoldarModel
import com.example.hospitalize.R

class GoldarListAdapter(var mCtx:Context , var resource:Int,var items:List<GoldarModel>)
    :ArrayAdapter<GoldarModel>( mCtx , resource , items ) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        var idText: TextView = view.findViewById(R.id.goldar_id)
        val goldar: TextView = view.findViewById(R.id.goldar_logo) as TextView
        val goldar_title: TextView = view.findViewById(R.id.goldar_title) as TextView
        val stok: TextView = view.findViewById(R.id.goldar_stock) as TextView
        val stokInt: TextView = view.findViewById(R.id.goldar_stok) as TextView


        var person: GoldarModel = items[position]

        idText.text = person.id
        goldar.text = person.goldar
        goldar_title.text = "Golongan Darah " +  person.goldar
        stok.text = "Tersedia " + person.stok + " kantong"
        stokInt.text = person.stok


        return view
    }
}
