package com.yssz.xiangxuedemo.service

import com.yssz.xiangxuedemo.bin.NewsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {
    @GET("article/list/{page}/json")
    fun getNewsItemData(@Path("page") page:Int): Call<NewsItem>
}