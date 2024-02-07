package com.rafdev.dayflow.ui.addtask

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.EditText
import android.widget.NumberPicker
import androidx.activity.viewModels
import com.rafdev.dayflow.R
import com.rafdev.dayflow.databinding.ActivityAddTaskBinding
import com.rafdev.dayflow.ui.addspent.DatePicker
import com.rafdev.dayflow.ui.onTextChanged
import dagger.hilt.android.AndroidEntryPoint
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

        initUI()

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

        binding.ivCalendar.setOnClickListener() {
            showPickerDialog()
        }


    }

    private fun initUI() {
        showCurrentDay()
        listeners()
        observers()
    }

    private fun observers() {
        viewModel.dataIsValid.observe(this) { isValid ->
            binding.btnSave.isEnabled = isValid
        }
    }

    private fun listeners() {
        binding.etName.onTextChanged { onFieldChanged() }
        binding.editTextDescription.onTextChanged { onFieldChanged() }
        val showDate = binding.tvDate.text.toString()
        val selectedHour = binding.hourPicker.value
        val selectedMinute = binding.minutePicker.value

        val hour =
            "${String.format("%02d", selectedHour)}:${String.format("%02d", selectedMinute)}"

        binding.btnSave.setOnClickListener {
            viewModel.insertNewNote(
                binding.etName.text.toString(),
                binding.editTextDescription.text.toString(),
                hour,
                showDate
            )
            finish()
        }
    }

    private fun onFieldChanged() {
        viewModel.onFieldsChanged(
            title = binding.etName.text.toString(),
            description = binding.editTextDescription.text.toString()
        )
    }


    private fun showCurrentDay() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateOfTheDay = dateFormat.format(calendar.time)
        binding.tvDate.text = dateOfTheDay
    }


    private fun showPickerDialog() {
        val datePicker = DatePicker { day, month, year -> onDataSelect(day, month, year) }
        datePicker.show(supportFragmentManager, "show calender")
    }

    private fun onDataSelect(day: Int, month: Int, year: Int) {
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
