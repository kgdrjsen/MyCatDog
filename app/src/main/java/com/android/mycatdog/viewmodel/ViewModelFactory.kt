package com.android.mycatdog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.mycatdog.retrofit.ImageInterface

class ViewModelFactory(private val retrofit : ImageInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(retrofit) as T
    }
}