package com.example.scheduleapp.ui.thirdFragment

import android.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.scheduleapp.databinding.FragmentThirdBinding
import com.example.scheduleapp.repositories.weekend
import com.example.scheduleapp.util


class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private lateinit var adapter:ThingsAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= ThingsAdapter(requireContext())
        setSpinnerAdapter()
        setListeners()
        setRecyclerViewAdapter()
    }

    private fun setListeners() {
        binding.spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                weekend.selectedItems=(p1 as AppCompatTextView).text.toString()
                weekend.listWithChecked= arrayListOf()
                adapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
private fun setRecyclerViewAdapter(){
    binding.rvCheckedText.layoutManager =
        GridLayoutManager(requireContext(), 3,GridLayoutManager.HORIZONTAL, false)
    binding.rvCheckedText.adapter = adapter
    adapter.notifyDataSetChanged()
}

    private fun setSpinnerAdapter(){
        val adapter =
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_item,
                util.listOfLessons()
            )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }
}