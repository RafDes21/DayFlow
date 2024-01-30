package com.rafdev.dayflow.ui.addtask

import android.content.Context
import android.content.Intent
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.NumberPicker
import android.widget.Toast
import com.rafdev.dayflow.R
import com.rafdev.dayflow.databinding.ActivityAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class AddTaskActivity : AppCompatActivity() {
    companion object {
        fun create(context: Context): Intent =
            Intent(context, AddTaskActivity::class.java)

    }

    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent
    private var isRecording = false
    private lateinit var audioFile: File

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaRecorder = MediaRecorder()
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        binding.btnRecordVoice.setOnClickListener {
            if (isRecording) {
                stopRecording()
            } else {
                startRecording()
            }
        }

        val hourPicker = findViewById<NumberPicker>(R.id.hourPicker)
        val minutePicker = findViewById<NumberPicker>(R.id.minutePicker)

        hourPicker.minValue = 1
        hourPicker.maxValue = 24

        minutePicker.minValue = 1
        minutePicker.maxValue = 59

        hourPicker.setFormatter { String.format("%02d", it) }
        minutePicker.setFormatter { String.format("%02d", it) }

        binding.btnSave.setOnClickListener {
            Log.i("audio", "audio prueba")
            if (isRecording) {
                Log.i("audio", "audio prueba pausa")

                // Si está grabando, detener la grabación
                stopRecording()
            }

            // Guardar la nota (descripción y hora)
            saveNote()
        }

        // Configurar el reconocimiento de voz
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(rmsdB: Float) {}

            override fun onBufferReceived(buffer: ByteArray?) {}

            override fun onEndOfSpeech() {}

            override fun onError(error: Int) {}

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    val spokenText = matches[0]
                    binding.editTextDescription.append(spokenText)
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {}

            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    private fun startRecording() {
        try {
            mediaRecorder.apply {
                reset()
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

                // Crear un archivo temporal para almacenar el audio
                audioFile = getTempFile()

                setOutputFile(audioFile.absolutePath)
                prepare()
                start()
            }
            isRecording = true
            binding.btnRecordVoice.setImageResource(R.drawable.ic_alarm)

            // Iniciar el reconocimiento de voz
            speechRecognizer.startListening(recognizerIntent)
        } catch (e: Exception) {
            // Manejar errores al iniciar la grabación
            e.printStackTrace()
        }
    }

    private fun stopRecording() {
        try {
            mediaRecorder.stop()
            mediaRecorder.release()
            isRecording = false
            binding.btnRecordVoice.setImageResource(R.drawable.ic_record_voice)

            // Detener el reconocimiento de voz
            speechRecognizer.stopListening()
        } catch (e: Exception) {
            // Manejar errores al detener la grabación
            e.printStackTrace()
        }
    }

    private fun getTempFile(): File {
        val folder = File(filesDir, "audio_recordings")
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return File(folder, "temp_audio.3gp")
    }

    private fun saveNote() {
        val selectedHour = binding.hourPicker.value
        val selectedMinute = binding.minutePicker.value
        val description = binding.editTextDescription.text.toString()

        // Ahora puedes utilizar 'audioFile' para trabajar con el archivo de audio
        // (subir a un servidor, mover a un directorio permanente, etc.)

        // Muestra los datos en un Toast
        val toastMessage = "Hora: $selectedHour:$selectedMinute\nDescripción: $description"
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }
}
