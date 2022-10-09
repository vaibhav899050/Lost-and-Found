package com.example.lostandfound

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(private val itemlists : ArrayList<item>): RecyclerView.Adapter<adapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.MyViewHolder {

        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.list_item,  parent, false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: adapter.MyViewHolder, position: Int) {

        val items : item = itemlists[position]
        holder.name.text = items.item
        holder.desc.text = items.desc
        holder.date.text = items.date
        holder.status.text = items.status
    }

    override fun getItemCount(): Int {

        return itemlists.size
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.titletxt)
        val desc: TextView = itemView.findViewById(R.id.desctxt)
        val status: TextView = itemView.findViewById(R.id.statustxt)
        val date: TextView = itemView.findViewById(R.id.datetxt)

    }
}