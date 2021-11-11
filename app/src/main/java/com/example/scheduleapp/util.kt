package com.example.scheduleapp

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.scheduleapp.entities.ScheduleEntity
import com.example.scheduleapp.entities.ScheduleObject
import com.google.gson.Gson

object util {
    val things: List<String> = arrayListOf(
        "Тетрадь",
        "Ручка",
        "Ноутбук",
        "Телефон",
        "Записная книжка",
        "Учебник",
        "бахилы"
    )
    val time: List<String> = arrayListOf(
        "8:00 - 9:35 ",
        "9:50 - 11:25 ",
        "11:55 - 13:30 ",
        "13:45 - 15:20 ",
        "15:50 - 17:25 ",
        "17:40 - 19:15 ",
        "19:30 - 21:00 "
    )
    var selectedLesson = ""


    private val gson = Gson()
    fun getScheduleFromSp(activity: Activity): ScheduleEntity {
        val mPrefs = activity.getSharedPreferences("ScheduleApp", Context.MODE_PRIVATE)
        val json = mPrefs.getString("Schedule", "")
        return gson.fromJson(json, ScheduleEntity::class.java)
    }

    fun putScheduleFromSp(activity: Activity, scheduleObject: ScheduleEntity) {
        val mPrefs = activity.getSharedPreferences("ScheduleApp", Context.MODE_PRIVATE)
        mPrefs.edit().putString("Schedule", gson.toJson(scheduleObject)).apply()
    }

    fun initSP(activity: Activity) {
        val mPrefs = activity.getSharedPreferences("ScheduleApp", Context.MODE_PRIVATE)
        if (mPrefs.getString("Schedule", "{}") == "{}") {
            val json = gson.toJson(ScheduleObject.ScheduleObject)
            mPrefs.edit().putString("Schedule", json).apply()
        }
    }
    fun removeSP(activity: Activity) {
        val mPrefs = activity.getSharedPreferences("ScheduleApp", Context.MODE_PRIVATE)
        mPrefs.edit().clear().apply()
    }

    object week {
        val monday: String = "Понедельник"
        val tuesday: String = "Вторник"
        val wednesday: String = "Среда"
        val thursday: String = "Четверг"
        val friday: String = "Пятница"
        val saturday: String = "Суббота"
        val sunday: String = "Воскресенье"
    }

    object day {
        val theoryOfInformation_practice: String = "Теория информации (практика)"
        val theoryOfInformation_lecture: String = "Теория информации (лекция)"
        val english: String = "Английский язык"
        val english2: String = "English"
        val superComputers_practice: String = "Суперкомпьютеры (практика)"
        val superComputers_lecture: String = "Суперкомпьютеры (лекция)"
        val theoryOfProbability_practice: String = "Теория вероятности (практика)"
        val theoryOfProbability_lecture: String = "Теория вероятности (лекция)"
        val dotNet_practice: String = ".NET (практика)"
        val dotNet_lecture: String = ".NET (лекция)"
        val computerGraphics_practice: String = "Компьютерная графика (практика)"
        val computerGraphics_lecture: String = "Компьютерная графика (лекция)"
        val mobileDevelop: String = "Мобильнпя разработка"
    }
}


