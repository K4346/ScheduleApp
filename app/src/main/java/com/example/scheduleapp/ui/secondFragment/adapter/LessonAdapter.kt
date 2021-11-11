package com.example.scheduleapp.ui.secondFragment.adapter

import android.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.scheduleapp.databinding.LessonItemBinding
import com.example.scheduleapp.util

class LessonAdapter(val context: Context) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    var list: ArrayList<String> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LessonAdapter.LessonViewHolder {
        val binding = LessonItemBinding
            .inflate(LayoutInflater.from(p0.context), p0, false)
        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonAdapter.LessonViewHolder, p1: Int) {
        val lessonName = list[p1]
        holder.lesson.setText(lessonName)
        holder.lesson.setAdapter(
            ArrayAdapter(
                context,
                R.layout.simple_dropdown_item_1line,
                lessons
            )
        )

        holder.binding.etLessonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                list[holder.adapterPosition] = s.toString()
            }
        })
    }


    override fun getItemCount() = list.size

    inner class LessonViewHolder(val binding: LessonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val lesson = binding.etLessonName

    }

    companion object {
        val lessons = arrayOf(
            util.day.theoryOfInformation_practice,
            util.day.theoryOfInformation_lecture,
            util.day.english,
            util.day.english2,
            util.day.superComputers_practice,
            util.day.superComputers_lecture,
            util.day.theoryOfProbability_practice,
            util.day.theoryOfProbability_lecture,
            util.day.dotNet_practice,
            util.day.dotNet_lecture,
            util.day.computerGraphics_practice,
            util.day.computerGraphics_lecture,
            util.day.mobileDevelop,
        )
    }
}