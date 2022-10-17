package com.example.lostandfound

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class adapter(private val itemlists : ArrayList<item>,
private val context: Context): RecyclerView.Adapter<adapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.MyViewHolder {

        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.list_item,  parent, false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: adapter.MyViewHolder, position: Int) {

        val items : item = itemlists[position]
        holder.name.text = items.item
        holder.desc.text = items.location
        holder.date.text = items.date
        holder.status.text = items.status
        holder.roll.text = items.roll
        val url = items.downloadurl
        Glide.with(context).load(url).into(holder.image)

    }

    override fun getItemCount(): Int {

        return itemlists.size
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.titletxt)
        val desc: TextView = itemView.findViewById(R.id.desctxt)
        val status: TextView = itemView.findViewById(R.id.statustxt)
        val date: TextView = itemView.findViewById(R.id.datetxt)
        val roll: TextView = itemView.findViewById(R.id.rolltxt)
        val image: ImageView = itemView.findViewById(R.id.itemimg)


    }
}