package com.example.scheduleapp.ui.thirdActivity

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scheduleapp.databinding.ThingsItemBinding
import com.example.scheduleapp.util

class ThingsAdapter(val activity: Activity) :
    RecyclerView.Adapter<ThingsAdapter.ThingsViewHolder>() {

    var list: List<String> = util.things
    var q = arrayListOf<String>()
    var currentThings: MutableList<String> = arrayListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ThingsViewHolder {
        val binding = ThingsItemBinding
            .inflate(LayoutInflater.from(p0.context), p0, false)

        return ThingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThingsViewHolder, p1: Int) {

        holder.thing.text = list[p1]
        if (util.getScheduleFromSp(activity).things!=null) {
            if (util.getScheduleFromSp(activity).things.containsKey(util.selectedLesson)) {
                if (util.getScheduleFromSp(activity).things.getValue(util.selectedLesson)
                        .contains(list[p1])
                ) {
                    holder.thing.isChecked = true
                } else {
                    holder.thing.isChecked = false
                }
            } else {
                holder.thing.isChecked = false
            }
        }
        if (util.selectedLesson.isNotEmpty()) {
            holder.thing.setOnClickListener {
                var listWithThings = arrayListOf<String>()
                val hashList = util.getScheduleFromSp(activity).things.orEmpty().toMutableMap()
                if (hashList.containsKey(util.selectedLesson)) {
                    listWithThings = hashList.getValue(util.selectedLesson)
                }
                val thing = holder.thing.text.toString()
                if (listWithThings.contains(thing)) {
                    listWithThings.remove(thing)
                } else {
                    listWithThings.add(holder.thing.text.toString())
                }

                hashList[util.selectedLesson] = listWithThings
                val jsonObject = util.getScheduleFromSp(activity)
                jsonObject.things = hashList
                util.putScheduleFromSp(activity, jsonObject)
                notifyDataSetChanged()
            }
        } else holder.thing.isChecked = false

    }


    override fun getItemCount() = list.size

    inner class ThingsViewHolder(binding: ThingsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val thing = binding.checkedText

    }

}