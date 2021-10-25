package com.example.retrofit_api_caching.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_api_caching.R
import com.example.retrofit_api_caching.model.Post

class MyAdapter(private val context : Context, private val userlist: List<Post>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var userId: TextView = itemView.findViewById(R.id.tv_userid)
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var imageView: ImageView = itemView.findViewById(R.id.iv_imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = userlist[position].name
        holder.title.text = userlist[position].message
        val profileImgURL = userlist[position].profileImage
        Glide.with(context).load(profileImgURL)
            .into(holder.imageView)

    }
}