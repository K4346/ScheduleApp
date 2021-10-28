package com.example.scheduleapp

import com.example.scheduleapp.repositories.weekend

object util {
        val things: List<String> = arrayListOf(
            "Тетрадь",
            "Ручка",
            "Ноутбук",
            "Телефон",
            "Записная книжка",
            "Учебник",
            "бахилы")
        fun listOfLessons():ArrayList<String>{
            val list = arrayListOf<String>()
            weekend.week.forEach {
                it?.lessons?.forEach { it1 ->
                    if (list.indexOf(it1) == -1) {
                        list.add(it1)
                    }
                }
            }
            return list
        }
}