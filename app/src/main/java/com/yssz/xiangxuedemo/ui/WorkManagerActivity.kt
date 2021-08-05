package com.yssz.xiangxuedemo.ui

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.yssz.xiangxuedemo.R
import com.yssz.xiangxuedemo.base.MyApplication
import com.yssz.xiangxuedemo.workmanager.SimpleWorker
import kotlinx.android.synthetic.main.activity_work_manager.*
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        doWorkBtn.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            WorkManager.getInstance(this).enqueue(request)
        }
//        val build =
//            PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()
//        WorkManager.getInstance(this).enqueue(build)
//        WorkManager.getInstance(this).cancelAllWork();

    }

    fun openDing(){
        val packageName = "com.alibaba.android.rimet"
        val packageManager = packageManager
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
             startActivity(intent)
        }
    }
}