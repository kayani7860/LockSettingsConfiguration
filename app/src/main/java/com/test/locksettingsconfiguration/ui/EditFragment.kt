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
import androidx.navigation.fragment.findNavController
import com.test.locksettingsconfiguration.R
import com.test.locksettingsconfiguration.databinding.FragmentSecondBinding
import com.test.locksettingsconfiguration.model.Parameter

class EditFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var primaryCheckedInt = 0
    private var secondaryCheckedInt = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

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

        setupClickListeners(parameter)
    }

    private fun setupClickListeners(parameter: Parameter?) {
        binding.buttonSave.setOnClickListener {
            parameter?.primaryValue = parameter?.dataModel?.values?.get(primaryCheckedInt)
            parameter?.secondaryValue = parameter?.dataModel?.values?.get(secondaryCheckedInt)
        }

        binding.buttonCancel.setOnClickListener {
            val action = EditFragmentDirections.actionSecondFragmentToFirstFragment()
            findNavController().navigate(action)
        }
    }

    private fun createRadioButtons(
        parameter: Parameter?, radioGroup: RadioGroup
    ) {

        if (radioGroup.childCount == 0){
            parameter?.dataModel?.values?.forEachIndexed { index, option ->
                val radioButton = RadioButton(requireContext())
                radioButton.text = option
                radioButton.id = index

                if (radioGroup == binding.radioGroupPrimary){
                    if(option == parameter.primaryValue){
                        radioButton.isChecked = true
                        primaryCheckedInt = radioButton.id
                    }
                }else{
                    if(option == parameter.secondaryValue){
                        radioButton.isChecked = true
                        secondaryCheckedInt = radioButton.id
                    }
                }

                radioGroup.addView(radioButton)
            }
        }


        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            radioGroup.check(checkedId)
            if (radioGroup == binding.radioGroupPrimary){
                primaryCheckedInt = radioGroup.checkedRadioButtonId
            } else{
                secondaryCheckedInt = radioGroup.checkedRadioButtonId
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}