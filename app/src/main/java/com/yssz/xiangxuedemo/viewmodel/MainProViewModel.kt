package com.yssz.xiangxuedemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yssz.xiangxuedemo.Fruit

class MainProViewModel(fruit: Fruit) : ViewModel() {
    private  var fruitLiveData = MutableLiveData<Fruit>()
    var fruitLiveData2 = MutableLiveData<String>()
    init {
        fruitLiveData.value=fruit
    }
    fun plusOne(){
        fruitLiveData.value=Fruit("gagagga",1)
    }
    fun clear(){
        fruitLiveData.value =Fruit("嚯嚯嚯",1)
    }
    val userName : LiveData<String> =Transformations.map(fruitLiveData){
        it.name
    }
    val userName2 : LiveData<String> =Transformations.switchMap(fruitLiveData){
        userName
    }
}