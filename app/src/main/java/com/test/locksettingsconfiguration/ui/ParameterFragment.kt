package com.test.locksettingsconfiguration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.locksettingsconfiguration.R
import com.test.locksettingsconfiguration.database.LockConfigManager
import com.test.locksettingsconfiguration.databinding.FragmentFirstBinding

class ParameterFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = listOf(
            DataModel("1", "true", "false"),
            DataModel("2", "faisalabad", "faisalabad"),
            DataModel("3", "malmo", "malmo"),
        )

        val listView = binding.listView
        val customAdapter = ParameterAdapter(requireContext(), dataList)
        listView.adapter = customAdapter
        val retrievedLockConfig = LockConfigManager.getLockConfig(requireContext())

        println("hammad retrievedLockConfig = $retrievedLockConfig")
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                customAdapter.filter(newText.orEmpty())
                return true
            }
        })


        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}