package com.yssz.xiangxuedemo.workmanager

import android.R.attr
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.yssz.xiangxuedemo.R
import kotlinx.android.synthetic.main.activity_ding_ding.*
import org.joda.time.DateTime
import org.joda.time.Duration
import java.util.concurrent.TimeUnit


class DingDingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ding_ding)
        val SELF_REMINDER_HOUR = 8
        var delay:Long

        if (DateTime.now().hourOfDay < SELF_REMINDER_HOUR) {
            delay = Duration(
                DateTime.now(),
                DateTime.now().withTimeAtStartOfDay().plusHours(SELF_REMINDER_HOUR)
            ).standardMinutes
        } else {
            delay = Duration(
                DateTime.now(),
                DateTime.now().withTimeAtStartOfDay().plusDays(1).plusHours(SELF_REMINDER_HOUR)
            ).standardMinutes
        }
        val workRequest = PeriodicWorkRequest.Builder(
            SimpleWorker::class.java,
            24,
            TimeUnit.HOURS,
            PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS,
            TimeUnit.MILLISECONDS
        )
            .setInitialDelay(delay, TimeUnit.MINUTES)
            .addTag("send_reminder_periodic")
            .build()

        dingBtn.setOnClickListener {
            WorkManager.getInstance()
                .enqueueUniquePeriodicWork("send_reminder_periodic", ExistingPeriodicWorkPolicy.REPLACE, workRequest);
        }
    }
}