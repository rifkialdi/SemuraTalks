package com.example.semuratalks.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.semuratalks.R
import com.example.semuratalks.NewsDetailActivity
import com.example.semuratalks.api.ArticlesItem
import com.example.semuratalks.databinding.ItemSearchBinding

class SearchAdapter(val context: Context, val itemSearch: ArrayList<ArticlesItem>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.idtvSumberberita.visibility = View.VISIBLE
        val data = itemSearch[position]
        holder.binding.idtvSumberberita.text = data.sourceItem.name
        holder.binding.idtvTitle.text = data.title
        Glide.with(holder.itemView)
            .load(data.urlToImage)
            .placeholder(R.drawable.loading)
            .centerCrop()
            .into(holder.binding.idrvImgsearch)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, NewsDetailActivity::class.java)
            intent.putExtra("URL", data.url)
            context.startActivity(intent)
        }

        holder.binding.idimgbtnShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, data.url)
            intent.type = "text/plain"
            holder.itemView.context.startActivity(Intent.createChooser(intent,"Share To: "))
        }
    }

    override fun getItemCount(): Int {
        return itemSearch.size
    }
}