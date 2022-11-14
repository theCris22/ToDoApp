package com.crisnavarro.todoapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.crisnavarro.todoapp.R
import com.crisnavarro.todoapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initViews()
    }

    private fun initViews() {
        val navHost = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHost
        setupActionBarWithNavController(navHost.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_nav_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}