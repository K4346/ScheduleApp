package com.example.scheduleapp.ui.SecondFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.scheduleapp.databinding.FragmentSecondBinding
import com.example.scheduleapp.ui.SecondFragment.adapter.LessonAdapter

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    val adapter = LessonAdapter()
    val k=arrayListOf("dsds","s","dsds","s","dsds","s")
    // This property is only valid between onCreateView and
    // onDestroyView.
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
        setListeners()
        binding.rvLessons.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvLessons.adapter=adapter
        adapter.list= k
        adapter.notifyDataSetChanged()

    }

    private fun setListeners() {
        binding.button.setOnClickListener {
            if (binding.spinnerDayOfWeekend.selectedItemId.toInt() == 0) {
                return@setOnClickListener
            }
            Toast.makeText(
                requireContext(),
                "${binding.spinnerDayOfWeekend.selectedItemId}",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.etAmountLessons.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString()!="")
                fillRecycler(s.toString().toInt())
            }
        })
    }

    fun  fillRecycler(i:Int){
        adapter.list = k.filterIndexed { index, s -> index < i }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}