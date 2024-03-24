package com.test.locksettingsconfiguration.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.test.locksettingsconfiguration.R
import com.test.locksettingsconfiguration.databinding.FragmentSecondBinding
import com.test.locksettingsconfiguration.model.Parameter

class EditFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setupUI() {
        val parameter = this.arguments?.getParcelable("parameterModel", Parameter::class.java)

        binding.title.text = parameter?.parameterName
        binding.tvPrimaryDefault.text =
            getString(R.string.defaultValue, parameter?.dataModel?.default)
        binding.tvSecondaryDefault.text =
            getString(R.string.defaultValue, parameter?.dataModel?.default)

        createRadioButtons(parameter, binding.radioGroupPrimary)
        createRadioButtons(parameter, binding.radioGroupSecondary)
    }

    private fun createRadioButtons(
        parameter: Parameter?, radioGroup: RadioGroup
    ) {
        parameter?.dataModel?.values?.forEach { option ->
            val radioButton = RadioButton(requireContext())
            radioButton.text = option
            radioGroup.addView(radioButton)
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = radioGroup.checkedRadioButtonId
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}