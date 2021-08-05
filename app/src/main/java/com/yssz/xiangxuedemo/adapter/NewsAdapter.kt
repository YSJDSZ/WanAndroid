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
import com.yssz.xiangxuedemo.bin.NewsItemDate

class NewsAdapter(val newsList: List<NewsItemDate>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author : TextView = view.findViewById(R.id.author)
        val title :TextView = view.findViewById(R.id.title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false);
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            Toast.makeText(parent.context, "you click view ${newsList[position].author}",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val newsItemDate = newsList[position]
        holder.author.text = newsItemDate.author
        holder.title.text = newsItemDate.title
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}