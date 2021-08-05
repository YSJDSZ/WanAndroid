package com.yssz.xiangxuedemo.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.yssz.xiangxuedemo.R
import com.yssz.xiangxuedemo.bin.NewsItem
import com.yssz.xiangxuedemo.bin.Register
import com.yssz.xiangxuedemo.service.NewsService
import com.yssz.xiangxuedemo.service.RegisterAndLoginService
import com.yssz.xiangxuedemo.service.ServiceCreator
import kotlinx.android.synthetic.main.activity_http.btnSendRequest
import kotlinx.android.synthetic.main.activity_retrofit.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RetrofitActivity : AppCompatActivity() {
    private val registerBo = Register("yangyl777", "777", "777")
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, RetrofitActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        btnSendRequest.setOnClickListener {

            val newsService = ServiceCreator.create<RegisterAndLoginService>()

            newsService.register(registerBo).enqueue(object :Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Toast.makeText(applicationContext,"注册成功",Toast.LENGTH_SHORT).show()
                   responseText.setText(response.body()?.string())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }
            })

    }
    }
    suspend fun getData(){
        val await = ServiceCreator.create<RegisterAndLoginService>().register(registerBo).await()
    }
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {continuation->
            enqueue(object :Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }
}