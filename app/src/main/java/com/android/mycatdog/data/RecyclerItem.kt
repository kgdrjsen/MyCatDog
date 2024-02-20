package com.android.mycatdog.data

import com.android.mycatdog.adapter.BreedListAdapter
import com.android.mycatdog.adapter.TopViewPager2Adapter

sealed class RecyclerItem {
    data class TopViewPager(
        var adapter: TopViewPager2Adapter
    ) : RecyclerItem()

//    data class Header(
//        var title : String
//    ) : RecyclerItem()

    data class BreedAdapter(
        var adapter : BreedListAdapter
    ) : RecyclerItem()
}
