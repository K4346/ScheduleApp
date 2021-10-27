package com.example.scheduleapp.repositories

import com.example.scheduleapp.entities.Day
import com.example.scheduleapp.entities.Lesson
import com.example.scheduleapp.entities.Week

data class DayModel(
    val dayOfWeek: Week,
    val lessons: ArrayList<Day>,
    val things: ArrayList<Lesson>,
)