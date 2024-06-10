package com.st10447889.weather_app_st10445996

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.st10447889.weather_app_st10445996.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val temperatures = intent.getDoubleArrayExtra("temperatures")?.toList() ?: listOf()

        val detailsListView: ListView = findViewById(R.id.detailsListView)
        val backButton: Button = findViewById(R.id.backButton)

        val details = temperatures.mapIndexed { index, temp ->
            "Day ${index + 1}: $temp°C"
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, details)
        detailsListView.adapter = adapter

        backButton.setOnClickListener {
            finish()
        }
    }
}
