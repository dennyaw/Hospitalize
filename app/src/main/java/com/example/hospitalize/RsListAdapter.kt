package com.example.hospitalize

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class RsListAdapter(var mCtx:Context , var resource:Int,var items:List<RsModel>)
    :ArrayAdapter<RsModel>( mCtx , resource , items ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        var idText: TextView = view.findViewById(R.id.rs_id)
        var name: TextView = view.findViewById(R.id.rs_name)
        var address: TextView = view.findViewById(R.id.rs_alamat)


        var person: RsModel = items[position]

        idText.text = person.id
        name.text = person.name
        address.text = person.address


        return view
    }
}
