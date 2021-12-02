package com.example.hospitalize

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.content.Intent
import android.widget.ImageButton


class rs_list_adapter(private val context: Activity, private val Id: Array<String>, private val Name: Array<String>, private val Alamat: Array<String>)
    : ArrayAdapter<String>(context, R.layout.rs_list, Name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.rs_list, null, true)

        val rs_name = rowView.findViewById(R.id.rs_name) as TextView
        val rs_alamat = rowView.findViewById(R.id.rs_alamat) as TextView

        rs_name.text = "${Name[position]}"
        rs_alamat.text = "${Alamat[position]}"

        return rowView
    }
}
