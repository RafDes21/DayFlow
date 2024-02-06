package com.rafdev.dayflow.ui.addtask

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.rafdev.dayflow.R
import com.rafdev.dayflow.databinding.ActivityAddTaskBinding
import com.rafdev.dayflow.domain.model.Note
import com.rafdev.dayflow.ui.addspent.DatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class AddTaskActivity : AppCompatActivity() {
    companion object {
        fun create(context: Context): Intent =
            Intent(context, AddTaskActivity::class.java)

        const val REQ_CODE_SPEECH_INPUT = 100

    }

    private lateinit var binding: ActivityAddTaskBinding

    private val viewModel: AddTaskViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mostrarFechaActual()

        val hourPicker = findViewById<NumberPicker>(R.id.hourPicker)
        val minutePicker = findViewById<NumberPicker>(R.id.minutePicker)

        hourPicker.minValue = 0
        hourPicker.maxValue = 24

        minutePicker.minValue = 0
        minutePicker.maxValue = 59

        hourPicker.setFormatter { String.format("%02d", it) }
        minutePicker.setFormatter { String.format("%02d", it) }

        binding.btnRecordVoice.setOnClickListener {
            startVoiceInput()
        }

        binding.btnSave.setOnClickListener {
            val textoIngresado = binding.editTextDescription.text.toString()
            val showDate = binding.tvDate.text.toString()
            Toast.makeText(this, "$showDate", Toast.LENGTH_SHORT).show()
            val selectedHour = hourPicker.value
            val selectedMinute = minutePicker.value

            val title = "title"
            val hour =
                "${String.format("%02d", selectedHour)}:${String.format("%02d", selectedMinute)}"
            viewModel.insertNewNote(title, textoIngresado, hour, showDate)
            finish()
        }

        binding.ivCalendar.setOnClickListener() {
            showPickerDialog()
        }

    }


    private fun mostrarFechaActual() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaActual = dateFormat.format(calendar.time)
        binding.tvDate.text = fechaActual
    }


    private fun showPickerDialog() {
        val datePicker = DatePicker { day, month, year -> onDataSelect(day, month, year) }
        datePicker.show(supportFragmentManager, "show calender")
    }

    private fun onDataSelect(day: Int, month: Int, year: Int) {
        Log.i("testprueba", "$day $month $year")

        val formattedDay = String.format("%02d", day)
        val formattedMonth = String.format("%02d", month)

        val date = "$formattedDay/$formattedMonth/$year"

        binding.tvDate.text = date
    }


    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Dime la nota que deseas agregar")
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (e: ActivityNotFoundException) {
            // Manejar la excepción si no hay actividad disponible para el reconocimiento de voz
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == RESULT_OK && data != null) {
                    val result: ArrayList<String>? =
                        data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    result?.get(0)?.let {
                        val editTextDescription: EditText = findViewById(R.id.editTextDescription)
                        editTextDescription.setText(it)
                    }
                }
            }
        }
    }

}
