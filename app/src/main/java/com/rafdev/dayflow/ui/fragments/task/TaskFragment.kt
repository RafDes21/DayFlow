package com.rafdev.dayflow.ui.fragments.task

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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
            isShow.observe(viewLifecycleOwner) {
                binding.noteMessage.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }

            notes.observe(viewLifecycleOwner) {
                binding.rvTask.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    val taskAdapter = TaskAdapter(it) {
//                        deleteNote(it)
                        showDetailFragment(it)
                    }
                    adapter = taskAdapter
                }
            }
        }
    }

    private fun showDetailFragment(it: NoteEntity) {
        findNavController().navigate(
            TaskFragmentDirections.actionTaskFragmentToTaskDetailFragment(
                id = it.id.toString()
            )
        )
    }

    private fun showDialogConfirmation(item: NoteEntity) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Confirmar eliminación")
            .setMessage("¿Estás seguro de que deseas eliminar este elemento?")
            .setPositiveButton("Sí") { dialog, which ->
                // Lógica para eliminar el elemento
                viewModel.deleteNote(item.id)
            }
            .setNegativeButton("No") { dialog, which ->
                // No hacer nada si se selecciona "No"
            }
            .show()
    }

    private fun deleteNote(item: NoteEntity) {
//        viewModel.deleteNote(item.id)
        showDialogConfirmation(item)
    }

    private fun showAddTask() {
        startActivity(AddTaskActivity.create(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}