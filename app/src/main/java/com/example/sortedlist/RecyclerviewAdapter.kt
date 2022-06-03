package com.example.sortedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter(private val data: MutableList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitle: TextView = itemView.findViewById(R.id.txtTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mTitle.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }
    fun refreshList() {
        notifyDataSetChanged()
    }


}


