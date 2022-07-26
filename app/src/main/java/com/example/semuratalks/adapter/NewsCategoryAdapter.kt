package com.example.semuratalks.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.semuratalks.NewsDetailActivity
import com.example.semuratalks.R
import com.example.semuratalks.api.DataItem
import com.example.semuratalks.databinding.ItemSearchBinding

class NewsCategoryAdapter(val item: DataItem) : RecyclerView.Adapter<NewsCategoryAdapter.NewsViewHolder>() {
    class NewsViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val data = item.posts[position]
        holder.binding.idtvTitle.text = data.title

        Glide.with(holder.itemView)
            .load(data.thumbnail)
            .placeholder(R.drawable.loading)
            .centerCrop()
            .into(holder.binding.idrvImgsearch)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, NewsDetailActivity::class.java)
            intent.putExtra("URL", data.link)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return item.posts.size
    }
}