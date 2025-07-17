package com.example.todo


import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date

data class Task(
    val id: Int = 0,
    val title: String,
    var created:Date
)

