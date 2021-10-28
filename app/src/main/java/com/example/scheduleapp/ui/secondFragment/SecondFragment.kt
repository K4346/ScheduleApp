package com.example.scheduleapp.ui.secondFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.scheduleapp.databinding.FragmentSecondBinding
import com.example.scheduleapp.repositories.weekend
import com.example.scheduleapp.ui.secondFragment.adapter.LessonAdapter


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var adapter: LessonAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LessonAdapter(requireContext())
        setListeners()
        setadapters()
//        val sp = requireContext.getSharedPreferences("Schedule",Context.MODE_PRIVATE)
//        val sharedPreferences = sp?.edit()?.put("","")?.apply()
    }

    private fun setadapters() {
        binding.rvLessons.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvLessons.adapter = adapter
    }

    private fun setListeners() {
        binding.button.setOnClickListener {
            val select = binding.spinnerDayOfWeekend.selectedItemId.toInt()
            if (select == 0) {
                return@setOnClickListener
            }

            weekend.week[select - 1]?.lessons = adapter.list as ArrayList<String>
            Toast.makeText(
                requireContext(),
                "${binding.spinnerDayOfWeekend.selectedItemId}",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.spinnerDayOfWeekend.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (binding.etAmountLessons.text.toString()!="") {
                    adapter.list = weekend.week[binding.spinnerDayOfWeekend.selectedItemId.toInt() - 1]?.lessons!!
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

    fun fillRecycler(i: Int) {
        if (i <= adapter.list.size) {
            adapter.list = adapter.list.filterIndexed { index, s -> index < i } as ArrayList<String>
        } else {
            for (j in 0 until i-adapter.list.size) {
                adapter.list.add("")
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}