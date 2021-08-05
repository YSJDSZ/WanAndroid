package com.yssz.xiangxuedemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.yssz.xiangxuedemo.R
import com.yssz.xiangxuedemo.zdy.showSnackbar
import com.yssz.xiangxuedemo.zdy.showToast
import kotlinx.android.synthetic.main.activity_collapsing.*

class CollapsingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsing)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.title="gagaga"
        Glide.with(this).load("https://wanandroid.com/blogimgs/18320a47-148a-4f8e-bf1a-71e633872dcf.png").into(imageView)
        imageView.setOnClickListener {

            imageView.showSnackbar("daindiandadd","action",Snackbar.LENGTH_LONG){
                "hh".showToast()
            }
        }
    }

}