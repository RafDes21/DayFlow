package com.rafdev.dayflow.ui.addspent

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.rafdev.dayflow.R
import com.rafdev.dayflow.databinding.ActivityAddSpentBinding
import com.rafdev.dayflow.ui.addtask.AddTaskActivity
import dagger.hilt.android.AndroidEntryPoint

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

    private fun addNewSpent() {
        val name = binding.etName.text.toString()
        val expenseAmount = binding.etExpenseAmount.text.toString().toFloatOrNull() ?: 0f
        val category = binding.actAutocomplete.text.toString()
        val description = binding.etDescription.text.toString()

        viewModel.insertNewSpent(name, expenseAmount, category, description)
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