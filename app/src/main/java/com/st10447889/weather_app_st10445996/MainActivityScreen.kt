package com.st10447889.weather_app_st10445996

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.st10447889.weather_app_st10445996.R

class MainActivityScreen : AppCompatActivity() {

    private lateinit var etTemperatureInput: EditText
    private lateinit var btnAddTemperature: Button
    private lateinit var tvAverageTemperature: TextView
    private lateinit var btnClearData: Button
    private lateinit var btnViewDailyDetails: Button

    private val temperatures = mutableListOf<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTemperatureInput = findViewById(R.id.etTemperatureInput)
        btnAddTemperature = findViewById(R.id.btnAddTemperature)
        tvAverageTemperature = findViewById(R.id.tvAverageTemperature)
        btnClearData = findViewById(R.id.btnClearData)
        btnViewDailyDetails = findViewById(R.id.btnViewDailyDetails)

        btnAddTemperature.setOnClickListener {
            val temperature = etTemperatureInput.text.toString().toDoubleOrNull()
            if (temperature != null) {
                temperatures.add(temperature)
                updateAverageTemperature()
                etTemperatureInput.text.clear()
            } else {
                etTemperatureInput.error = "Please enter a valid temperature"
            }
        }

        btnClearData.setOnClickListener {
            temperatures.clear()
            updateAverageTemperature()
        }

        btnViewDailyDetails.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("temperatures", temperatures.toDoubleArray())
            startActivity(intent)
        }
    }

    private fun updateAverageTemperature() {
        if (temperatures.isNotEmpty()) {
            val average = temperatures.average()
            tvAverageTemperature.text = "Average Temperature: $average°C"
        } else {
            tvAverageTemperature.text = "No temperatures recorded"
        }
    }
}
