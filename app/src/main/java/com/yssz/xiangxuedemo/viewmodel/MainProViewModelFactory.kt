package com.yssz.xiangxuedemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yssz.xiangxuedemo.Fruit

class MainProViewModelFactory(private val countReserved: Fruit) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainProViewModel(countReserved) as T
    }
}