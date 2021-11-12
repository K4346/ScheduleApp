package com.example.scheduleapp.ui.firstActivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.scheduleapp.databinding.ActivityFirstBinding
import com.example.scheduleapp.ui.secondActivity.SecondActivity
import com.example.scheduleapp.ui.thirdActivity.ThirdActivity
import com.example.scheduleapp.util
import java.util.*

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        util.initSP(this)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resetButton.setOnClickListener {
            util.removeSP(this)
            util.initSP(this)
        }

        binding.leftButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
        binding.rightButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.cv.setOnDateChangeListener { calendarView, i, i2, i3 ->
            val calendar: Calendar = Calendar.getInstance();
            calendar.set(i, i2, i3);
            val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
            val args = Bundle()

            args.putInt("DAY", dayOfWeek)
            val dayDialog = DayDialog()
            dayDialog.arguments = args
            dayDialog.show(supportFragmentManager, "")
        }
    }

}