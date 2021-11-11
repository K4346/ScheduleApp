package com.example.scheduleapp.ui.firstActivity

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.example.scheduleapp.util


class DayDialog() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val args = arguments

        val schedule = util.getScheduleFromSp(this.requireActivity())
        var dayOfWeek = args?.getInt("DAY")
        if (dayOfWeek == 0) {
            dayOfWeek = 7
        }
        val lessons = schedule.days[dayOfWeek!! - 1].lessons
        var things: MutableList<String> = arrayListOf()
        var s = ""

        lessons?.forEachIndexed {i,it->
            if (schedule.things.containsKey(it)) {
                things = (schedule.things.getValue(it))
                if (it.isNotEmpty()){
                var thingsString = ""
                things.forEach { thing -> thingsString = thingsString + thing + " " }
                s =s + util.time[i] + it + " : " + thingsString + "\n"}
                else s += "\n"
            } else {
                if (it.isNotEmpty()){
                s =  s + util.time[i] + it + "\n"
            } else {
                    s += "\n"
                }
            }
        }

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(
                schedule.days[dayOfWeek - 1].DayName
//                weekend.week[dayOfWeek - 1]?.dayOfWeek
            )
                .setMessage(s)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}