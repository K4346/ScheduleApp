package com.example.scheduleapp.ui.SecondFragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scheduleapp.databinding.LessonItemBinding

class LessonAdapter(): RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
    var list:List<String> = ArrayList()
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
        val lessonName=list[p1]
    holder.lesson.setText(lessonName)
    }

    override fun getItemCount()=list.size

    inner class LessonViewHolder(val binding:LessonItemBinding): RecyclerView.ViewHolder(binding.root) {
        val lesson=binding.etLessonName
    }
}