package com.example.scheduleapp.repositories

import com.example.scheduleapp.entities.Day
import com.example.scheduleapp.entities.Lesson

data class DayModel(
    val dayOfWeek: String,
    val lessons: ArrayList<Day>,
    val things: ArrayList<Lesson>,
)