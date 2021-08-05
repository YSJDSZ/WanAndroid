package com.yssz.xiangxuedemo.workmanager

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.ResolveInfo
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.yssz.xiangxuedemo.base.MyApplication
import java.text.SimpleDateFormat
import java.util.*

class SimpleWorker(context:Context,params:WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期格式
        Log.d("SimpleWorker", "！！！！do work in SimpleWorker:${df.format(Date())}")

        openDing()
        return Result.success()
    }
    fun openDing(){
        val packageName = "com.alibaba.android.rimet"
        val packageManager = MyApplication.context.packageManager
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        var resolveIntent = Intent(Intent.ACTION_MAIN, null)
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageInfo.packageName);
        val apps : List<ResolveInfo> = packageManager.queryIntentActivities(resolveIntent, 0)
        val resolveInfo = apps.iterator().next()
        if (resolveInfo != null ) {
            val className = resolveInfo.activityInfo.name
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val cn = ComponentName(packageName, className)
            intent.setComponent(cn)
            MyApplication.context.startActivity(intent)
        }
    }
}