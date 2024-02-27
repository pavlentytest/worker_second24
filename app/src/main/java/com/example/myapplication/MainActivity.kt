package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data: Data = Data.Builder().putString("key1","hello").build()

        val worker = OneTimeWorkRequestBuilder<MyWorker>()
            .setInputData(data)
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
    }
}