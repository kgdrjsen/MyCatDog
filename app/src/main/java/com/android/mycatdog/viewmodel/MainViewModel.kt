package com.android.mycatdog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mycatdog.data.Constants
import com.android.mycatdog.data.MyItems
import com.android.mycatdog.data.RandomItems
import com.android.mycatdog.retrofit.ImageClient
import com.android.mycatdog.retrofit.ImageInterface
import kotlinx.coroutines.launch

class MainViewModel(private val retrofit : ImageInterface) : ViewModel() {
    private var _searchRandomImages : MutableLiveData<List<RandomItems>> = MutableLiveData(mutableListOf())
    val searchRandomImages : LiveData<List<RandomItems>> get() = _searchRandomImages
    private var _searchImages : MutableLiveData<List<MyItems>> = MutableLiveData(mutableListOf())
    val searchImages : LiveData<List<MyItems>> get() = _searchImages
    private var _myItmes : MutableLiveData<MutableList<MyItems>> = MutableLiveData(mutableListOf())
    val myItmes : LiveData<MutableList<MyItems>>
        get() = _myItmes

    fun randomSearch() {
        var randomItems : MutableList<RandomItems> = mutableListOf()
        val imgNetwork = ImageClient.imgNetWork

        viewModelScope.launch {
            randomItems.clear()
            val responseImg = imgNetwork.imageRandomSearch()
            val randomImg = RandomItems(responseImg.id,responseImg.url)
            randomItems.add(randomImg)
        }
        _searchRandomImages.value = randomItems
    }

    fun doSearch(breeds : String) {
        var searchItems : MutableList<MyItems> = mutableListOf()
        val imgNetwork = ImageClient.imgNetWork

        viewModelScope.launch {
            searchItems.clear()
            val responseImg = imgNetwork.imageBreedsSearch(Constants.API_KEY,10,breeds)
            responseImg.breeds.forEach {
                val searchImg = MyItems(responseImg.id,responseImg.url,
                    it.id,it.name,it.origin,it.description,it.temperament)
                searchItems.add(searchImg)
            }
        }
    }

    fun likeToggle(item : MyItems) {
        if (_myItmes.value?.contains(item) == true) {
            _myItmes.value?.remove(item)
        }else {
            _myItmes.value?.add(item)
        }
    }
}
