package com.example.hospitalize

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.content.Intent
import android.view.LayoutInflater
import android.widget.ImageButton


class rs_list_adapter(private val context: Activity, private val Id: Array<String>, private val Name: Array<String>, private val Alamat: Array<String>)
    : ArrayAdapter<String>(context, R.layout.rs_list, Name) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater:LayoutInflater = context.layoutInflater
        val view2: View = inflater.inflate(R.layout.rs_list, null, true)

        val rs_name = view2.findViewById(R.id.rs_name) as TextView
        val rs_alamat = view2.findViewById(R.id.rs_alamat) as TextView

        rs_name.text = Name[position]
        rs_alamat.text = Alamat[position]

        return view2
    }
}
