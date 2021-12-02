package com.example.hospitalize.adapter

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalize.R
import com.example.hospitalize.database.RsEntity

class RsViewAdapter(val listener: RowClickListener): RecyclerView.Adapter<RsViewAdapter.MyViewHolder>() {
    var items = ArrayList<RsEntity>()

    fun setListData(data: ArrayList<RsEntity>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RsViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.rs_list, parent, false)
        return  MyViewHolder(inflater, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RsViewAdapter.MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])
    }

    class MyViewHolder(view: View, val listener: RowClickListener): RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.rs_name)
        val address = view.findViewById<TextView>(R.id.rs_alamat)
        val rs = view.findViewById<TextView>(R.id.rs)

        fun bind(data: RsEntity) {
            name.text = data.name
            address.text = data.address
            rs.setOnClickListener {
                listener.onRsClickListener(data)
            }
        }
    }

    interface RowClickListener {
        fun onRsClickListener(user: RsEntity)
        fun onItemClickListener(user: RsEntity)
    }
}