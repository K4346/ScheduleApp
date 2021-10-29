package com.example.scheduleapp.ui.firstActivity

import android.R
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.scheduleapp.databinding.FragmentFirstBinding
import com.example.scheduleapp.entities.Week
import com.example.scheduleapp.repositories.weekend
import com.example.scheduleapp.ui.firstFragment.DayDialog
import com.example.scheduleapp.ui.secondActivity.SecondActivity
import com.example.scheduleapp.ui.thirdActivity.ThirdActivity
import java.util.*

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK)
            Toast.makeText(applicationContext, "$i , $i2 , $i3, ${dayOfWeek}", Toast.LENGTH_SHORT)
                .show()
            val args = Bundle()

            val day: String = when (dayOfWeek) {
                1 -> Week.monday
                2 -> Week.tuesday
                3 -> Week.wednesday
                4 -> Week.thursday
                5 -> Week.friday
                6 -> Week.saturday
                else -> Week.sunday
            }
            weekend.week[dayOfWeek - 1]?.dayOfWeek = day
            args.putInt("DAY", dayOfWeek)
            val dayDialog = DayDialog()
            dayDialog.arguments = args
            dayDialog.show(supportFragmentManager, "")
        }
    }

}