package com.android.mycatdog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.mycatdog.apidata.RecyclerItem
import com.android.mycatdog.databinding.ItemRecyclerViewBinding
import com.android.mycatdog.databinding.ItemViewpager2Binding

class MainRecyclerViewAdapter(val context: Context) : ListAdapter<RecyclerItem,ViewHolder> (
    object : ItemCallback<RecyclerItem>() {
        override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
            return oldItem == newItem
        }

    }
) {
    private val TOP_TYPE = 0
    private val BOTTOM_TYPE = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType) {
            TOP_TYPE ->{
                val topItem = ItemViewpager2Binding.inflate(inflater,parent,false)
                TopViewHolder(topItem)}
            else -> {
                val bottomItem = ItemRecyclerViewBinding.inflate(inflater,parent,false)
                BottomViewHolder(bottomItem)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if (item is RecyclerItem.TopViewPager) {
            with(holder as TopViewHolder) {
                pager.adapter = item.adapter
            }
        }
        if (item is RecyclerItem.BreedAdapter) {
            with(holder as BottomViewHolder) {
                rvBottom.adapter = item.adapter
                rvBottom.layoutManager = GridLayoutManager(context,2)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is RecyclerItem.TopViewPager -> TOP_TYPE
            is RecyclerItem.BreedAdapter -> BOTTOM_TYPE
        }

    }
    class TopViewHolder(binding: ItemViewpager2Binding) : ViewHolder(binding.root) {
        val pager = binding.viewPager2
        val left = binding.back
        val right = binding.front

        init {
            left.setOnClickListener {
                val current = pager.currentItem
                if (current == 0) {
                    pager.setCurrentItem(9,false)
                } else {
                    pager.setCurrentItem(current-1,false)
                }
            }

            right.setOnClickListener {
                val current = pager.currentItem
                if (current == 9) {
                    pager.setCurrentItem(0,false)
                }else {
                    pager.setCurrentItem(current+1,false)
                }
            }
        }
    }

    class BottomViewHolder(binding: ItemRecyclerViewBinding) : ViewHolder(binding.root) {
        val rvBottom = binding.rvBottomView
    }
}
