package com.test.locksettingsconfiguration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.test.locksettingsconfiguration.api.ParameterService
import com.test.locksettingsconfiguration.api.RetrofitHelper
import com.test.locksettingsconfiguration.databinding.ActivityMainBinding
import com.test.locksettingsconfiguration.repository.ParameterRepository
import com.test.locksettingsconfiguration.viewModels.MainViewModel
import com.test.locksettingsconfiguration.viewModels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val parameterService = RetrofitHelper.getInstance().create(ParameterService::class.java)
        val repository = ParameterRepository(parameterService)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]


        mainViewModel.parameters.observe(this) {
            println("hammad observe ${it?.name}")
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}