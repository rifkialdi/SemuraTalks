package com.example.semuratalks.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.semuratalks.R
import com.example.semuratalks.api.PathsItem
import com.example.semuratalks.databinding.ItemNewsBinding

class NewsAdapter(val item: List<PathsItem>, val onItemClickCallback: OnItemClickCallback) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.idtvCategory.text = item[position].name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onClick(item[position].name)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    interface OnItemClickCallback {
        fun onClick(name: String)
    }
}