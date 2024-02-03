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
import android.widget.Toast
import androidx.activity.viewModels
import com.rafdev.dayflow.R
import com.rafdev.dayflow.databinding.ActivityAddTaskBinding
import com.rafdev.dayflow.domain.model.Note
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
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

        val hourPicker = findViewById<NumberPicker>(R.id.hourPicker)
        val minutePicker = findViewById<NumberPicker>(R.id.minutePicker)

        hourPicker.minValue = 1
        hourPicker.maxValue = 24

        minutePicker.minValue = 1
        minutePicker.maxValue = 59

        hourPicker.setFormatter { String.format("%02d", it) }
        minutePicker.setFormatter { String.format("%02d", it) }

        binding.btnRecordVoice.setOnClickListener {
            startVoiceInput()
        }

        binding.btnSave.setOnClickListener {
            val textoIngresado = binding.editTextDescription.text.toString()

            val selectedHour = hourPicker.value
            val selectedMinute = minutePicker.value

            val mensaje = "Texto ingresado: $textoIngresado\nHora: ${
                String.format(
                    "%02d",
                    selectedHour
                )
            }:${String.format("%02d", selectedMinute)}"

            val hour = "${String.format("%02d",selectedHour)}:${String.format("%02d", selectedMinute)}"


            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            viewModel.insertNewNote(textoIngresado, hour)

            finish()


        }

    }

    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Escribe lo que deseas")
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
