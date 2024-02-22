package com.solutionchallenge.unitmap

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.solutionchallenge.unitmap.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var newsList: ArrayList<NewsItems>
    private lateinit var newsAdapter: newsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("onView create")
        auth = FirebaseAuth.getInstance()
        db = Firebase.firestore
        newsList = ArrayList()

        binding.buttonSignOut.setOnClickListener {
            auth.signOut()
            val intent = Intent(requireContext(), MainActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding.buttonMyHelps.setOnClickListener {
            val intent = Intent(requireContext(), MyReportsActivity::class.java)
            requireActivity().startActivity(intent)

        }

        binding.recyclerViewNews.layoutManager = LinearLayoutManager(requireContext())
        getData()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        //en son paylaşılan en yukarda
        db.collection("News")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
                } else {
                    value?.let {
                        if (!value.isEmpty) {
                            val doc = value.documents

                            newsList.clear()
                            for (document in doc) {
                                //casting
                                val downloadUrl = document.get("downloadUrl") as String
                                val news = NewsItems(
                                    downloadUrl = downloadUrl
                                )
                                newsList.add(news)
                                println("$news")
                            }
                            newsAdapter = newsAdapter(newsList)
                            binding.recyclerViewNews.adapter = newsAdapter
                            newsAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
    }
}