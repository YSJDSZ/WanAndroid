package com.yssz.xiangxuedemo.http

import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import kotlin.concurrent.thread

object OkHttpUtils {
    fun getHttp(address:String): String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(address)
            .build()
        var response = client.newCall(request).execute()
        var body  = response.body()?.string()
        return body
    }
    fun post(listener:Callback){
        thread {
            try {
                val client = OkHttpClient()
                val requestBody = FormBody.Builder()
                    .add("username", "yangyl113")
                    .add("password", "123456")
                    .add("repassword", "123456")
                    .build()
                val request = Request.Builder()
                    .url("https://www.wanandroid.com/user/register")
                    .post(requestBody)
                    .build()
                client.newCall(request).enqueue(listener)
            } catch (e: Exception) {
            }
        }
    }
}