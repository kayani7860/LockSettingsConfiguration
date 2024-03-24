package com.test.locksettingsconfiguration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.locksettingsconfiguration.databinding.FragmentParameterBinding
import com.test.locksettingsconfiguration.viewModels.ParameterViewModel

class ParameterFragment : Fragment() {

    private var _binding: FragmentParameterBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: ParameterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParameterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this)[ParameterViewModel::class.java]

        val parameterAdapter = setupAdapter()
        setupSearch(parameterAdapter)
    }

    private fun setupAdapter(): ParameterAdapter {
        val listView = binding.listView
        listView.layoutManager = LinearLayoutManager(requireContext())

        val parameterAdapter = ParameterAdapter(requireContext()) { model ->
            val action = ParameterFragmentDirections.actionFirstFragmentToSecondFragment(model)
            findNavController().navigate(action)
        }

        listView.adapter = parameterAdapter
        mainViewModel.parameters.observe(viewLifecycleOwner) { parameterList ->

            if (parameterList.isNullOrEmpty()) {
                binding.loadingProgressBar.visibility = View.GONE
                binding.clMain.visibility = View.VISIBLE
                parameterAdapter.originalList = parameterList
                parameterAdapter.submitList(parameterList)
            }
        }
        return parameterAdapter
    }

    private fun setupSearch(parameterAdapter: ParameterAdapter) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                parameterAdapter.filter(newText)
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}