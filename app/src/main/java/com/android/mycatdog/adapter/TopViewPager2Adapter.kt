package com.android.mycatdog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.mycatdog.data.RandomItems
import com.android.mycatdog.databinding.ItemHorizonViewBinding
import com.bumptech.glide.Glide

class TopViewPager2Adapter : ListAdapter<RandomItems, TopViewPager2Adapter.TopViewHolder> (RandomDiffUtil) {
    object RandomDiffUtil : DiffUtil.ItemCallback<RandomItems>() {
        override fun areItemsTheSame(oldItem: RandomItems, newItem: RandomItems): Boolean {
            return oldItem.urlId == newItem.urlId
        }

        override fun areContentsTheSame(oldItem: RandomItems, newItem: RandomItems): Boolean {
            return oldItem == newItem
        }
    }

    inner class TopViewHolder(binding: ItemHorizonViewBinding) : ViewHolder(binding.root) {
        val img = binding.ivViewPager2Thumbnails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHorizonViewBinding.inflate(inflater,parent,false)
        return TopViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(getItem(position).url)
            .into(holder.img)
    }
}