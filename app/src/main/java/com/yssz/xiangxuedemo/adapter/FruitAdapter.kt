package com.yssz.xiangxuedemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.yssz.xiangxuedemo.Fruit
import com.yssz.xiangxuedemo.R

class FruitAdapter(val fruitList: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage : ImageView = view.findViewById(R.id.fruitImage)
        val fruitName :TextView = view.findViewById(R.id.fruitName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item,parent,false);
        val viewHolder = ViewHolder(view)
        viewHolder.fruitImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            Toast.makeText(parent.context, "you click image ${fruitList[position].name}",Toast.LENGTH_SHORT).show()
        }
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            Toast.makeText(parent.context, "you click view ${fruitList[position].name}",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.setText(fruit.name)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }
}