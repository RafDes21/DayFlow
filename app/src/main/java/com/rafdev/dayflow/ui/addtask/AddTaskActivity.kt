package com.rafdev.dayflow.ui.addtask

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import com.rafdev.dayflow.R
import com.rafdev.dayflow.databinding.ActivityAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent =
            Intent(context, AddTaskActivity::class.java)

    }

    private lateinit var binding: ActivityAddTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hourPicker = findViewById<NumberPicker>(R.id.hourPicker)
        val minutePicker = findViewById<NumberPicker>(R.id.minutePicker)


        hourPicker.minValue = 1
        hourPicker.maxValue = 24

        minutePicker.minValue = 1
        minutePicker.maxValue = 59

        hourPicker.setFormatter { String.format("%02d", it) }
        minutePicker.setFormatter { String.format("%02d", it) }
        binding.btnSave.setOnClickListener {

            val selectedHour = hourPicker.value
            val selectedMinute = minutePicker.value

            // Mostrar los datos en un Toast
            val toastMessage = "Hora: $selectedHour:$selectedMinute\nDescripción"
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }

    }
}