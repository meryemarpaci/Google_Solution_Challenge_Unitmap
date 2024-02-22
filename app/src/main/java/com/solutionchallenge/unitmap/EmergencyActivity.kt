package com.solutionchallenge.unitmap

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.solutionchallenge.unitmap.databinding.ActivityEmergencyBinding

class EmergencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmergencyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)
        binding = ActivityEmergencyBinding.inflate(layoutInflater)


        //buttonMyLocation basınca haritadan konumu göstersin

    }

    fun helpButton(view: View) {
        Toast.makeText(this, "Successfully Help Request Sent", Toast.LENGTH_LONG).show()

    }

}