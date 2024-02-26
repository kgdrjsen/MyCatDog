package com.android.mycatdog.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mycatdog.adapter.BreedListAdapter
import com.android.mycatdog.adapter.MainRecyclerViewAdapter
import com.android.mycatdog.adapter.TopViewPager2Adapter
import com.android.mycatdog.apidata.RecyclerItem
import com.android.mycatdog.databinding.FragmentCatBinding
import com.android.mycatdog.retrofit.ImageClient
import com.android.mycatdog.viewmodel.MainViewModel
import com.android.mycatdog.viewmodel.ViewModelFactory

class  CatFragment : Fragment() {
    private var _binding: FragmentCatBinding? = null
    private val binding get() = _binding!!

    private val mainAdapter by lazy { MainRecyclerViewAdapter(requireContext()) }
    private val topAdapter by lazy { TopViewPager2Adapter() }
    private val breedAdapter by lazy { BreedListAdapter() }

    private val retrofit = ImageClient.imgNetWork
    private val mainViewModel : MainViewModel by activityViewModels { ViewModelFactory(retrofit) }

    private var catFragItems : MutableList<RecyclerItem> = mutableListOf(
        RecyclerItem.TopViewPager(topAdapter),
        RecyclerItem.BreedAdapter(breedAdapter)
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
        setObserve()
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

    private fun setObserve() {
        mainViewModel.searchRandomImages.observe(viewLifecycleOwner){
            topAdapter.submitList(mainViewModel.searchRandomImages.value)
            Log.d("CatFragment","#aaa Random size = ${mainViewModel.searchRandomImages.value?.size}")
            mainAdapter.submitList(catFragItems)
        }
    }
}