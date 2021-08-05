package com.yssz.xiangxuedemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yssz.xiangxuedemo.Fruit
import com.yssz.xiangxuedemo.R
import com.yssz.xiangxuedemo.lifecycle.MyObserver
import com.yssz.xiangxuedemo.viewmodel.MainProViewModel
import com.yssz.xiangxuedemo.viewmodel.MainProViewModelFactory
import com.yssz.xiangxuedemo.viewmodel.MainViewModel
import com.yssz.xiangxuedemo.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_view_model.*
import kotlin.math.log

class ViewModelActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainProViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        lifecycle.addObserver(MyObserver())
        Log.d("state:","currentState1:${lifecycle.currentState}")
        mainViewModel =   ViewModelProvider(this, MainProViewModelFactory(Fruit("改改尴尬",1))).get(MainProViewModel::class.java)
        plusOneBtn.setOnClickListener {
            mainViewModel.plusOne()
        }
        clearBtn.setOnClickListener {
            mainViewModel.clear()
        }
        mainViewModel.userName.observe(this) {
            infoText.text=it.toString()
        }
        showText()
    }

    fun showText(){
        infoText.text=mainViewModel.userName.toString()
    }
}