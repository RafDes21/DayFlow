package com.rafdev.dayflow.ui.fragments.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import com.rafdev.dayflow.databinding.FragmentTaskBinding
import com.rafdev.dayflow.ui.addtask.AddTaskActivity
import com.rafdev.dayflow.ui.fragments.task.adapter.TaskAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

    }

    private fun initUI() {
        binding.apply {
            btnAddTask.setOnClickListener {
                showAddTask()
            }
        }

        observers()
//        listeners()
    }

//    private fun listeners() {
//        binding.
//    }

    private fun observers() {
        viewModel.apply {
            notes.observe(viewLifecycleOwner) {
                binding.rvTask.apply {
                    layoutManager = LinearLayoutManager(context)
                    val taskAdapter = TaskAdapter(it) {
                        deleteNote(it)
                    }
                    adapter = taskAdapter
                }
            }
        }
    }

    private fun deleteNote(item: NoteEntity) {
        viewModel.deleteNote(item.id)
    }

    private fun showAddTask() {
        startActivity(AddTaskActivity.create(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}