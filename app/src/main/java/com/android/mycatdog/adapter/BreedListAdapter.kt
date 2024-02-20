package com.android.mycatdog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.mycatdog.data.MyItems
import com.android.mycatdog.databinding.ItemRecyclerListBinding
import com.bumptech.glide.Glide

class BreedListAdapter : ListAdapter<MyItems,BreedListAdapter.BreedItemViewHolder>(BreedDiffUtil) {
    object BreedDiffUtil : DiffUtil.ItemCallback<MyItems>() {
        override fun areItemsTheSame(oldItem: MyItems, newItem: MyItems): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: MyItems, newItem: MyItems): Boolean {
            return oldItem == newItem
        }
    }

    inner class BreedItemViewHolder(binding: ItemRecyclerListBinding) : ViewHolder(binding.root) {
        val img = binding.ivThumbnails
        val breed = binding.tvBreedId
        val description = binding.tvDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerListBinding.inflate(inflater,parent,false)
        return BreedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedItemViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(currentList[position].url)
            .into(holder.img)
        holder.breed.text = currentList[position].breedName
        holder.description.text = currentList[position].description
    }
}