package com.yssz.xiangxuedemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yssz.xiangxuedemo.R
import com.yssz.xiangxuedemo.http.HttpCallBackListener
import com.yssz.xiangxuedemo.http.HttpUtils
import com.yssz.xiangxuedemo.http.OkHttpUtils
import kotlinx.android.synthetic.main.activity_http.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.Exception
import kotlin.concurrent.thread

class HttpUrlConnectionAct : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, HttpUrlConnectionAct::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http);
        btnSendRequest.setOnClickListener(View.OnClickListener {
            sendRequest()

        })
        responseText.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,WebViewActivity::class.java)
            startActivity(intent)

        })
    }


    private fun sendRequest2(){
       thread {
           var response = HttpUtils.httpGet()
           showResponse(response)
       }
   }
    private fun sendRequest(){
        OkHttpUtils.post(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                showResponse(e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()?.string()
                if (responseBody != null) {
                    showResponse(responseBody)
                }
            }

        })
    }
    private fun showResponse(response: String) {
        runOnUiThread{
            // 在这里进行UI操作，将结果显示到界面上
            responseText.text = response
        }
    }
}