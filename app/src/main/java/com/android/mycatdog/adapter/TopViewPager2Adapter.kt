package com.android.mycatdog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.mycatdog.data.RandomItems
import com.android.mycatdog.databinding.ItemViewpager2Binding
import com.bumptech.glide.Glide

//class TopViewPager2Adapter : RecyclerView.Adapter<TopViewPager2Adapter.ViewHolder>() {
//    lateinit var items : ArrayList<RandomItems>
//
//    fun build(i:ArrayList<RandomItems>) : TopViewPager2Adapter {
//        items = i
//        return this
//    }
//
//    inner class ViewHolder(binding : ItemViewpager2Binding) : RecyclerView.ViewHolder(binding.root) {
//        val img = binding.ivViewPager2Thumbnails
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): TopViewPager2Adapter.ViewHolder =
//        ViewHolder(ItemViewpager2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
//
//    override fun onBindViewHolder(holder: TopViewPager2Adapter.ViewHolder, position: Int) {
//        Glide.with(holder.itemView.context)
//            .load(items[position].url)
//            .into(holder.img)
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//}

class TopViewPager2Adapter : ListAdapter<RandomItems, TopViewPager2Adapter.TopViewHolder> (RandomDiffUtil) {
    object RandomDiffUtil : DiffUtil.ItemCallback<RandomItems>() {
        override fun areItemsTheSame(oldItem: RandomItems, newItem: RandomItems): Boolean {
            return oldItem.urlId == newItem.urlId
        }

        override fun areContentsTheSame(oldItem: RandomItems, newItem: RandomItems): Boolean {
            return oldItem == newItem
        }
    }

    inner class TopViewHolder(binding: ItemViewpager2Binding) : ViewHolder(binding.root) {
        val img = binding.ivViewPager2Thumbnails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewpager2Binding.inflate(inflater,parent,false)
        return TopViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(currentList[position].url)
            .into(holder.img)
    }
}