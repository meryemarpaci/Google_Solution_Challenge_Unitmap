package com.solutionchallenge.unitmap

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.solutionchallenge.unitmap.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageNaturalDisasters.setOnClickListener {
            val intent = Intent(requireContext(), DisastersActivity::class.java)
            requireActivity().startActivity(intent)

        }


    }


}