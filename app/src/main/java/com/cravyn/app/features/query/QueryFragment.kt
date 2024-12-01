package com.cravyn.app.features.query

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.DialogRaiseQueryBinding
import com.cravyn.app.databinding.FragmentQueryBinding
import com.cravyn.app.features.query.adapters.QueriesRecyclerViewAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QueryFragment : Fragment() {
    private var _binding: FragmentQueryBinding? = null
    private val binding get() = _binding!!

    private val queryViewModel: QueryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQueryBinding.inflate(inflater, container, false)

        binding.raiseQueryButton.setOnClickListener {
            val binding = DialogRaiseQueryBinding.inflate(LayoutInflater.from(requireContext()))
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle("Raise Query")
                .setView(binding.root)
                .setPositiveButton("Submit") { dialog, _ ->
                    queryViewModel.raiseQuery(
                        binding.questionTextInputLayout.editText?.text.toString()
                    )
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        queryViewModel.getQueries()

        queryViewModel.queriesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    binding.queriesLoadingBar.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    binding.queriesLoadingBar.isVisible = true
                }

                is Resource.Success -> {
                    binding.queriesLoadingBar.isVisible = false
                    binding.queriesRecyclerView.adapter =
                        QueriesRecyclerViewAdapter(it.data?.customerQueries ?: emptyList())
                }
            }
        }

        queryViewModel.raiseQueryLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    queryViewModel.getQueries()
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
