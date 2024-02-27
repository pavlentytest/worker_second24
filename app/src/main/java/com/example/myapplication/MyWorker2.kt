package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker2(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("RRR","MyWorker2 started")
        Thread.sleep(5000)
        Log.d("RRR","MyWorker2 completed!")
        return Result.success()
    }
}