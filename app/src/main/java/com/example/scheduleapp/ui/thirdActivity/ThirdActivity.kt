package com.example.scheduleapp.ui.thirdActivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.scheduleapp.databinding.ActivityThirdBinding
import com.example.scheduleapp.ui.firstActivity.FirstActivity
import com.example.scheduleapp.ui.secondActivity.SecondActivity
import com.example.scheduleapp.util

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var adapter: ThingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ThingsAdapter(this)
        setSpinnerAdapter()
        setListeners()
        setRecyclerViewAdapter()
    }

    private fun setListeners() {
        binding.leftButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        binding.rightButton.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }

        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                util.selectedLesson = (p1 as TextView).text.toString()
                adapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        binding.spinner.post { binding.spinner.setOnItemSelectedListener(listener) }
    }

    private fun setRecyclerViewAdapter() {
        binding.rvCheckedText.layoutManager =
            GridLayoutManager(applicationContext, 3, GridLayoutManager.HORIZONTAL, false)
        binding.rvCheckedText.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun setSpinnerAdapter() {
        val adapter =
            ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_item,
                getLessonsList()
            )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        util.selectedLesson = if (getLessonsList().size!=0) getLessonsList()[0] else ""
    }

    private fun getLessonsList(): ArrayList<String> {
        val lessons = arrayListOf<String>()
        util.getScheduleFromSp(this).days.forEach { schedule ->
            schedule.lessons?.map { lesson ->
                if (!lessons.contains(lesson)) {
                    if (lesson.isNotEmpty()){
                    lessons.add(lesson)
                }}
            }
        }
        return lessons
    }
}