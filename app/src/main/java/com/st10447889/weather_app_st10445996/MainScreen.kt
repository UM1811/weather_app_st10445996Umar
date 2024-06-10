package com.st10447889.weather_app_st10445996

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainScreen : AppCompatActivity() {
    private lateinit var Day: EditText
    private lateinit var Morning: EditText
    private lateinit var Afternoon: EditText
    private lateinit var Condition: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonDisplay: Button

    private var dayList = mutableListOf<Int>()
    private var morningList = mutableListOf<Float>()
    private var afternoonList = mutableListOf<Float>()
    private var notesList = mutableListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        Day = findViewById(com.st10447889.weather_app_st10445996.R.id.etDay)
        Morning = findViewById(com.st10447889.weather_app_st10445996.R.id.etMorning)
        Afternoon = findViewById(com.st10447889.weather_app_st10445996.R.id.etAfternoon)
        Condition = findViewById(com.st10447889.weather_app_st10445996.R.id.etNotes)
        buttonSave = findViewById(com.st10447889.weather_app_st10445996.R.id.buttonSave)
        buttonClear = findViewById(com.st10447889.weather_app_st10445996.R.id.buttonClear)
        buttonDisplay = findViewById(com.st10447889.weather_app_st10445996.R.id.buttonDisplay)

        buttonClear.setOnClickListener {
            Day.setText("")
            Morning.setText("")
            Afternoon.setText("")
            Condition.setText("")
        }

        buttonSave.setOnClickListener {
            val realday = Day.text.toString()
            val realmorning = Morning.text.toString()
            val realafternoon = Afternoon.text.toString()
            val realnotes = Condition.text.toString()
            if (realday.isNotEmpty() && realmorning.isNotEmpty() && realafternoon.isNotEmpty()) {
                try {
                    dayList.add(realday.toInt())
                    morningList.add(realmorning.toFloat())
                    afternoonList.add(realafternoon.toFloat())
                    notesList.add(realnotes)
                    Day.setText("")
                    Morning.setText("")
                    Afternoon.setText("")
                    Condition.setText("")
                } catch (e: NumberFormatException) {
                    // Handle exception if needed, e.g., show a Toast message
                }
            }
        }

        buttonDisplay.setOnClickListener {
            val intent2 = Intent(this, DisplayActivity::class.java)
            intent2.putExtra("dayArray", dayList.toIntArray())
            intent2.putExtra("morningArray", morningList.toFloatArray())
            intent2.putExtra("afternoonArray", afternoonList.toFloatArray())
            intent2.putExtra("notesArray", notesList.toTypedArray())
            startActivity(intent2)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(com.st10447889.weather_app_st10445996.R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
