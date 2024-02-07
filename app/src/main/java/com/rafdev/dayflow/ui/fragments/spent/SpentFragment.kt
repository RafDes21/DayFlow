package com.rafdev.dayflow.ui.fragments.spent

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafdev.dayflow.data.db.enteties.SpentEntity
import com.rafdev.dayflow.databinding.FragmentSpentBinding
import com.rafdev.dayflow.ui.addspent.AddSpentActivity
import com.rafdev.dayflow.ui.fragments.spent.adapter.SpentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpentFragment : Fragment() {

    private var _binding: FragmentSpentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpentBinding.inflate(inflater, container, false)
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
        viewModel.apply {
            isShow.observe(viewLifecycleOwner) {
                binding.message.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
            spent.observe(viewLifecycleOwner) {
                binding.rvSpent.apply {
                    layoutManager = LinearLayoutManager(context)
                    val spentAdapter = SpentAdapter(it) {
                        showDialogConfirmation(it.id)                     }
                    adapter = spentAdapter
                }
            }
        }
    }

    private fun showDialogConfirmation(id: Int) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Confirmar eliminación")
            .setMessage("¿Estás seguro de que deseas eliminar este elemento?")
            .setPositiveButton("Sí") { dialog, which ->
                viewModel.deleteSpent(id)
            }
            .setNegativeButton("No") { dialog, which ->
                // No hacer nada si se selecciona "No"
            }
            .show()
    }

    private fun listeners() {
        binding.showAddSpent.setOnClickListener {
            showAddSpent()
        }
    }

    private fun showAddSpent() {
        startActivity(AddSpentActivity.create(requireContext()))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}