package com.example.myapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val data: Data = Data.Builder().putString("key1","hello").build()

        val constraint = Constraints.Builder().setRequiresCharging(true).build()

        val worker5 = PeriodicWorkRequestBuilder<MyWorker2>(1, TimeUnit.MINUTES).build() // 15 min - min

        val worker6 = PeriodicWorkRequestBuilder<MyWorker2>(1, TimeUnit.HOURS, 15, TimeUnit.MINUTES);


        val worker = OneTimeWorkRequestBuilder<MyWorker>()
            .setInputData(data)
            .addTag("worker2")
            .setConstraints(constraint)
            .build()

        val worker2 = OneTimeWorkRequestBuilder<MyWorker2>().build()

        val list: ArrayList<WorkRequest> = ArrayList();
        list.add(worker)
        list.add(worker2)

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {

            // Параллельно
            WorkManager.getInstance(this).enqueue(list)


            // Последовательный
           /* WorkManager.getInstance(this)
                .beginWith(worker)
                .then(worker2)
                .enqueue()

            */
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(worker.id)
            .observe(this, Observer { workerData: WorkInfo ->
                if(workerData != null) {
                    Log.d("RRR",workerData.state.toString())
                    workerData.outputData.getString("key2")?.let { Log.d("RRR", it) }
                }
            })
    }
}