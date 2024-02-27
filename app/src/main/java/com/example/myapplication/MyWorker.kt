package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val str = inputData.getString("key1")
        Log.d("RRR","MyWorker1 started")
        Thread.sleep(3000)
        Log.d("RRR","MyWorker1 completed!")
        val data: Data = Data.Builder().putString("key2","hello").build()
        return Result.success(data)
    }
}