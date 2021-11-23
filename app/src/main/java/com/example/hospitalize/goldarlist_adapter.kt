package com.example.hospitalize

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class goldarlist_adapter(private val context: Activity, private val Goldar: Array<String>, private val Stok: Array<String>)
    : ArrayAdapter<String>(context, R.layout.activity_goldar_list, Goldar) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.activity_goldar_list, null, true)

        val goldar = rowView.findViewById(R.id.goldar_logo) as TextView
        val goldar_title = rowView.findViewById(R.id.goldar_title) as TextView
        val stok = rowView.findViewById(R.id.goldar_stock) as TextView

        goldar.text = "${Goldar[position]}"
        goldar_title.text = "Golongan Darah ${Goldar[position]}"
        stok.text = "Tersedia ${Stok[position]} kantong"
        return rowView
    }
}
