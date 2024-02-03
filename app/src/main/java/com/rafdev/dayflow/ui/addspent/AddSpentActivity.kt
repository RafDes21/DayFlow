package com.rafdev.dayflow.ui.addspent

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    }
}