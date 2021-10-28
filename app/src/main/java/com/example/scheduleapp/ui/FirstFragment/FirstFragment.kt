package com.example.scheduleapp.ui.FirstFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.scheduleapp.databinding.FragmentFirstBinding
import com.example.scheduleapp.repositories.DayModel
import com.example.scheduleapp.repositories.weekend
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cv.setOnDateChangeListener { calendarView, i, i2, i3 ->
            val calendar: Calendar = Calendar.getInstance();
            calendar.set(i, i2, i3);
            val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK)
            Toast.makeText(requireContext(), "$i , $i2 , $i3, ${dayOfWeek}", Toast.LENGTH_SHORT)
                .show()
            val args = Bundle()
            args.putStringArrayList("Понедельник", weekend.week[0]?.lessons)
            args.putStringArrayList("Вторник", weekend.week[1]?.lessons)
            args.putStringArrayList("Среда", weekend.week[2]?.lessons)
            args.putStringArrayList("Понедельник", weekend.week[3]?.lessons)
            args.putStringArrayList("Понедельник", weekend.week[4]?.lessons)
            args.putStringArrayList("Понедельник", weekend.week[5]?.lessons)
            args.putParcelableArrayList("DAY", weekend.week)
            val k =args.getParcelableArrayList<DayModel>("DAY")
            args.putStringArrayList("THINGS", arrayListOf())
            val dayDialog = DayDialog()
            dayDialog.arguments = args
            dayDialog.show(fragmentManager, "")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}