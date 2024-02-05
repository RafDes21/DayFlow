package com.rafdev.dayflow.ui.addspent

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSpentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        genderOption()
    }

    private fun genderOption() {
        val genderOptions = resources.getStringArray(R.array.categories)

        val genderAdapter = ArrayAdapter(this, R.layout.list_categories, genderOptions)
        binding.actAutocomplete.setAdapter(genderAdapter)
        binding.actAutocomplete.setOnItemClickListener { _, _, position, _ ->
            val selectedGender = genderAdapter.getItem(position)
            Toast.makeText(this, "Género seleccionado: $selectedGender", Toast.LENGTH_SHORT).show()
        }
    }
}