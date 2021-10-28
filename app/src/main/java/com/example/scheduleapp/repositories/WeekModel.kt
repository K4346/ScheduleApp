package com.example.scheduleapp.repositories

import android.util.ArrayMap
import com.example.scheduleapp.entities.Week

object weekend {
    val week:ArrayList<DayModel?> = arrayListOf(DayModel(),DayModel(),DayModel(),DayModel(),DayModel(),DayModel(),DayModel())
    var selectedItems:String=""
    var things: MutableMap<String, List<String>>? = ArrayMap()
    var listWithChecked:MutableList<String> = arrayListOf()
}