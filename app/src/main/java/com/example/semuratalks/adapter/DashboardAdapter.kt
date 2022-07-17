package com.example.semuratalks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.semuratalks.R
import com.example.semuratalks.databinding.ItemRvdashboardBinding
import com.example.semuratalks.model.DashboardModel

class DashboardAdapter(val itemList: ArrayList<DashboardModel>) : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    class DashboardViewHolder(val binding: ItemRvdashboardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return DashboardViewHolder(ItemRvdashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val data = itemList[position]

        holder.binding.idtvDashboard.text = data.platformBerita
        Glide.with(holder.itemView)
            .load(data.image)
            .placeholder(R.drawable.ic_launcher_background)
            .centerInside()
            .into(holder.binding.idrvImgdashboard)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}