package com.example.scheduleapp.ui.secondActivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import com.example.scheduleapp.databinding.FragmentSecondBinding
import com.example.scheduleapp.ui.firstActivity.FirstActivity
import com.example.scheduleapp.ui.thirdActivity.ThirdActivity
import com.example.scheduleapp.util


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var adapter: LessonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = LessonAdapter(applicationContext)
        setListeners()
        setadapters()
//        val sp = requireContext.getSharedPreferences("Schedule",Context.MODE_PRIVATE)
//        val sharedPreferences = sp?.edit()?.put("","")?.apply()
    }

    private fun setadapters() {
        binding.rvLessons.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.rvLessons.adapter = adapter
    }

    private fun setListeners() {
        binding.leftButton.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
        binding.rightButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val select = binding.spinnerDayOfWeekend.selectedItemId.toInt()
            if (select == 0) {
                return@setOnClickListener
            }

            val jsonObject = util.getScheduleFromSp(this)
            jsonObject.days[select - 1].lessons = adapter.list
            util.putScheduleFromSp(this, jsonObject)
        }
        binding.spinnerDayOfWeekend.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (binding.etAmountLessons.text.toString() != "") {
                        var l =
                            util.getScheduleFromSp(this@SecondActivity).days[binding.spinnerDayOfWeekend.selectedItemId.toInt() - 1].lessons
                        if (l == null) {
                            l = arrayListOf()
                        }
                        adapter.list = l
                        fillRecycler(binding.etAmountLessons.text.toString().toInt())
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        binding.etAmountLessons.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty())
                    fillRecycler(s.toString().toInt())
            }
        })
    }

    fun fillRecycler(p: Int) {
        var i = p
        if (i > 7) {
            i = 7
        }
        if (i <= adapter.list.size) {
            adapter.list = adapter.list.filterIndexed { index, s -> index < i } as ArrayList<String>
        } else {
            for (j in 0 until i - adapter.list.size) {
                adapter.list.add("")
                adapter.notifyDataSetChanged()
            }
        }
    }
}