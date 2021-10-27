package com.example.scheduleapp.ui.FirstFragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.Toast
import com.example.scheduleapp.repositories.DayModel

class DayDialog(): DialogFragment(){
private val catNames = arrayOf("Добавить в избранное", "Погладить")

override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val args=arguments
    val day=args?.getString("DAY")
    val lessons = args?.getStringArrayList("LESSONS")
    val things = args?.getStringArrayList("THINGS")
    var s:String = ""
   lessons?.forEach { s=s+it+'\n' }
    return activity?.let {
        val builder = AlertDialog.Builder(it)
        builder.setTitle(day)
            .setMessage(s)
        builder.create()
    } ?: throw IllegalStateException("Activity cannot be null")
}
}