package com.android.mycatdog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.mycatdog.data.MyItems
import com.android.mycatdog.databinding.ItemListViewBinding
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

    inner class BreedItemViewHolder(binding: ItemListViewBinding) : ViewHolder(binding.root) {
        val img = binding.ivThumbnails
        val breed = binding.tvBreedId
        val description = binding.tvDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListViewBinding.inflate(inflater,parent,false)
        return BreedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedItemViewHolder, position: Int) {
        val item = getItem(position)

        Glide.with(holder.itemView.context)
            .load(item.url)
            .into(holder.img)
        holder.breed.text = item. breedName
        holder.description.text = item.description
    }
}