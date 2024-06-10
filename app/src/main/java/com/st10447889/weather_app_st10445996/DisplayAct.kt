package com.st10447889.weather_app_st10445996

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DisplayActivity : AppCompatActivity() {
    private lateinit var tvDay: TextView
    private lateinit var tvMorning: TextView
    private lateinit var tvAfternoon: TextView
    private lateinit var tvNotes: TextView
    private lateinit var buttonBack: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_display)

        tvDay = findViewById(R.id.tvDay)
        tvMorning = findViewById(R.id.tvMorning)
        tvAfternoon = findViewById(R.id.tvAfternoon)
        tvNotes = findViewById(R.id.tvNotes)
        buttonBack = findViewById(R.id.buttonBack)

        buttonBack.setOnClickListener {
            finish()
        }

        val dayArray = intent.getIntArrayExtra("dayArray")
        val morningArray = intent.getFloatArrayExtra("morningArray")
        val afternoonArray = intent.getFloatArrayExtra("afternoonArray")
        val notesArray = intent.getStringArrayExtra("notesArray")

        val dayBuilder = StringBuilder()
        val morningBuilder = StringBuilder()
        val afternoonBuilder = StringBuilder()
        val notesBuilder = StringBuilder()

        if (dayArray != null) {
            for ((index, day) in dayArray.withIndex()) {
                dayBuilder.append("Day ${index + 1}: $day\n")
            }
        }

        if (morningArray != null) {
            for ((index, morning) in morningArray.withIndex()) {
                morningBuilder.append("Morning ${index + 1}: $morning\n")
            }
        }

        if (afternoonArray != null) {
            for ((index, afternoon) in afternoonArray.withIndex()) {
                afternoonBuilder.append("Afternoon ${index + 1}: $afternoon\n")
            }
        }

        if (notesArray != null) {
            for ((index, note) in notesArray.withIndex()) {
                notesBuilder.append("Note ${index + 1}: $note\n")
            }
        }

        tvDay.text = dayBuilder.toString()
        tvMorning.text = morningBuilder.toString()
        tvAfternoon.text = afternoonBuilder.toString()
        tvNotes.text = notesBuilder.toString()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
