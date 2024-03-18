package com.test.locksettingsconfiguration.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.locksettingsconfiguration.repository.ParameterRepository

class MainViewModelFactory(private val repository: ParameterRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}