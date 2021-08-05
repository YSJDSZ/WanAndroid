package com.yssz.xiangxuedemo.service

import com.yssz.xiangxuedemo.bin.Register
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterAndLoginService {
    @POST("user/register")
    fun register(@Body body:Register): Call<ResponseBody>
}