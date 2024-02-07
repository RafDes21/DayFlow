package com.rafdev.dayflow.ui.addspent

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.rafdev.dayflow.R
import com.rafdev.dayflow.databinding.ActivityAddSpentBinding
import com.rafdev.dayflow.ui.addtask.AddTaskActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class AddSpentActivity : AppCompatActivity() {
    companion object {
        fun create(context: Context): Intent =
            Intent(context, AddSpentActivity::class.java)

    }

    private lateinit var binding: ActivityAddSpentBinding
    private val viewModel: AddSpentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSpentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        mostrarFechaActual()
        binding.ivCalendar.setOnClickListener() {
            showPickerDialog()
            val showDate = binding.tvDate.text.toString()
            Toast.makeText(this, "$showDate", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initUI() {
        categoryOption()
        listeners()
    }

    private fun listeners() {
        binding.btnSaveExpense.setOnClickListener {
            addNewSpent()
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

    private fun addNewSpent() {
        val name = binding.etName.text.toString()
        val expenseAmount = binding.etExpenseAmount.text.toString().toFloatOrNull() ?: 0f
        val category = binding.actAutocomplete.text.toString()
        val description = binding.etDescription.text.toString()

        val showDate = binding.tvDate.text.toString()
        viewModel.insertNewSpent(name, expenseAmount, category, description, showDate)
        finish()
    }

    private fun categoryOption() {
        val options = resources.getStringArray(R.array.categories)

        val categoryAdapter = ArrayAdapter(this, R.layout.list_categories, options)
        binding.actAutocomplete.setAdapter(categoryAdapter)
        binding.actAutocomplete.setOnItemClickListener { _, _, position, _ ->
            val selectedGender = categoryAdapter.getItem(position)
            Toast.makeText(this, "Género seleccionado: $selectedGender", Toast.LENGTH_SHORT).show()
        }
    }
}