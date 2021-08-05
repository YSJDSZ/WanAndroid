package com.yssz.xiangxuedemo.http

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

object HttpUtils {
      fun httpGet():String{
        val url = URL("https://www.baidu.com")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = 8000
        connection.readTimeout = 8000
        val input = connection.inputStream
          var reader = BufferedReader(InputStreamReader(input))
        var response = StringBuilder()
        reader.use {
          reader.forEachLine {
            response.append(it)
          }
        }
          connection.disconnect()
        return response.toString()
    }
}