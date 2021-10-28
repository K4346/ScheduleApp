package com.example.scheduleapp.ui.firstFragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.example.scheduleapp.repositories.weekend

class DayDialog() : DialogFragment() {
    private val catNames = arrayOf("Добавить в избранное", "Погладить")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val args = arguments
        val dayOfWeek = args?.getInt("DAY")
        val day = weekend.week[dayOfWeek!! - 1]
        val lessons = weekend.week[dayOfWeek - 1]?.lessons
        var things: MutableList<String> = arrayListOf()
        var s: String = ""
        lessons?.forEach {
            if (weekend.things?.containsKey(it) == true) {
                things = (weekend.things?.getValue(it) as MutableList<String>)
                var thingsString = ""
                things.forEach { thing->thingsString = thingsString + thing +" "}
                s = s + it + " : " + thingsString + "\n"
            }
        }

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(weekend.week[dayOfWeek - 1]?.dayOfWeek)
                .setMessage(s)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}