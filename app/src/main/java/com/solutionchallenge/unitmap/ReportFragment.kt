package com.solutionchallenge.unitmap

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solutionchallenge.unitmap.databinding.FragmentReportBinding

class ReportFragment : Fragment() {
    private lateinit var binding: FragmentReportBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonEmergency.setOnClickListener {
            val intent=Intent(requireContext(),EmergencyActivity::class.java)
            requireActivity().startActivity(intent)
        }

        binding.buttonMyHelps.setOnClickListener {
            val intent=Intent(requireContext(),MyHelpsActivity::class.java)
            requireActivity().startActivity(intent)
        }

        binding.buttonActiveProblems.setOnClickListener {
            val intent=Intent(requireContext(),ActiveProblemsActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }


}