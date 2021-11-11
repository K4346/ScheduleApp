package com.example.scheduleapp.entities

import android.util.ArrayMap
import com.example.scheduleapp.util

object ScheduleObject {
    private val daysList: ArrayList<DayEntity> = arrayListOf(
        DayEntity(util.week.monday),
        DayEntity(util.week.tuesday),
        DayEntity(util.week.wednesday),
        DayEntity(util.week.thursday),
        DayEntity(util.week.friday),
        DayEntity(util.week.saturday),
        DayEntity(util.week.sunday),
    )
    val ScheduleObject = ScheduleEntity(daysList)
}

data class ScheduleEntity(
    var days: ArrayList<DayEntity>,
    var things: MutableMap<String, ArrayList<String>> = ArrayMap()
)

data class DayEntity(
    val DayName: String,
//    var lessons: ArrayList<Lesson>? = null
    var lessons: ArrayList<String>? = null

)

data class Lesson(
    var lessonName: String,
    var things: ArrayList<String>
)