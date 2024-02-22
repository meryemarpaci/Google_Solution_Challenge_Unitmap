package com.solutionchallenge.unitmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.solutionchallenge.unitmap.databinding.ActivityDisastersBinding

class DisastersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisastersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisastersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonBeSolution.setOnClickListener {
            val reportFragment = ReportFragment()
            replace(ReportFragment())

        }


    }

    private fun replace(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.constrainLayoutNat, fragment).commit()
    }
}