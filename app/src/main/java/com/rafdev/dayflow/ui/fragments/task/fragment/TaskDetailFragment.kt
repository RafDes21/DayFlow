package com.rafdev.dayflow.ui.fragments.task.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import com.rafdev.dayflow.databinding.FragmentTaskDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailFragment : Fragment() {

    private val args: TaskDetailFragmentArgs by navArgs()

    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TaskDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        listeners()
        observers()
    }

    private fun observers() {
        viewModel.result.observe(viewLifecycleOwner) {
            updateView(it)
        }
    }

    private fun updateView(it: NoteEntity?) {
        binding.apply {
            tvTitle.text = it?.title
            tvDate.text = it?.date
            tvDescription.text = it?.description
            tvHour.text = it?.hour
        }
    }

    private fun listeners() {
        val id = args.id.toInt()
        viewModel.getNote(id)
        binding.ivDelete.setOnClickListener {
            showDialogConfirmation(id)
        }
    }


    private fun showDialogConfirmation(id: Int) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Confirmar eliminación")
            .setMessage("¿Estás seguro de que deseas eliminar este elemento?")
            .setPositiveButton("Sí") { dialog, which ->
                viewModel.deleteNote(id)
                findNavController().popBackStack()
            }
            .setNegativeButton("No") { dialog, which ->
                // No hacer nada si se selecciona "No"
            }
            .show()
    }

}