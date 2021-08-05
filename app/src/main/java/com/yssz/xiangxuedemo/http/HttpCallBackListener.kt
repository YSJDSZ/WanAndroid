package com.yssz.xiangxuedemo.http

import java.lang.Exception

interface HttpCallBackListener {
    fun onSuccess(response:String)
    fun onError(e:Exception)
}