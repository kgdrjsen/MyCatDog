package com.android.mycatdog.view

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.android.mycatdog.R
import com.android.mycatdog.adapter.BreedListAdapter
import com.android.mycatdog.adapter.MainRecyclerViewAdapter
import com.android.mycatdog.adapter.TopViewPager2Adapter
import com.android.mycatdog.data.RecyclerItem
import com.android.mycatdog.databinding.FragmentCatBinding
import com.android.mycatdog.retrofit.ImageClient
import com.android.mycatdog.viewmodel.MainViewModel
import com.android.mycatdog.viewmodel.ViewModelFactory
import kotlinx.coroutines.Job

class  CatFragment : Fragment() {
    private var _binding: FragmentCatBinding? = null
    private val binding get() = _binding!!

    private val mainAdapter by lazy { MainRecyclerViewAdapter(requireContext()) }
    private val topAdapter by lazy { TopViewPager2Adapter() }
    private val breedAdapter by lazy { BreedListAdapter() }

    private val retrofit = ImageClient.imgNetWork
    private val mainViewModel : MainViewModel by activityViewModels { ViewModelFactory(retrofit) }

    private var catFragItems : MutableList<RecyclerItem> = mutableListOf(
        RecyclerItem.TopViewPager(TopViewPager2Adapter()),
        RecyclerItem.BreedAdapter(BreedListAdapter())
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        initViews()
    }

    private fun initViews() {
        mainViewModel.randomSearch()
        initMainRecyclerView()
    }

    private fun initMainRecyclerView() {
        with(binding.recyclerviewCat) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        mainAdapter.submitList(catFragItems)
    }
}