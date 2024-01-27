package com.rafdev.dayflow.ui.addtask

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}