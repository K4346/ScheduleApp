package com.example.scheduleapp.ui.thirdFragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scheduleapp.databinding.ThingsItemBinding
import com.example.scheduleapp.repositories.weekend
import com.example.scheduleapp.util

class ThingsAdapter(val context: Context) : RecyclerView.Adapter<ThingsAdapter.ThingsViewHolder>() {

    var list: List<String> = util.things

    var currentThings:MutableList<String> = arrayListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ThingsAdapter.ThingsViewHolder {
        val binding = ThingsItemBinding
            .inflate(LayoutInflater.from(p0.context), p0, false)

        return ThingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThingsAdapter.ThingsViewHolder, p1: Int) {
        val thingName = list[p1]
        holder.thing.text = thingName
      if (weekend.things?.containsKey(weekend.selectedItems) == true) {
          ( weekend.things?.getValue(weekend.selectedItems) as MutableList<String>).forEach {
              if (holder.thing.text.toString()==it) {
                  holder.thing.isChecked = true
              }
          }
        } else {
            holder.thing.isChecked=false
        }
        holder.thing.setOnClickListener {
            holder.thing.isChecked = !holder.thing.isChecked
            if (holder.thing.isChecked){
                if (weekend.listWithChecked.indexOf(holder.thing.text.toString())==-1){
                    weekend.listWithChecked.add(holder.thing.text.toString())
                weekend.things?.put(weekend.selectedItems,weekend.listWithChecked)
                }
            }
        }
    }


    override fun getItemCount() = list.size

    inner class ThingsViewHolder(val binding: ThingsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val thing = binding.checkedText

    }

}