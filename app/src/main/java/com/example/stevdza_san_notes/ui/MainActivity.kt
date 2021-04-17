package com.example.stevdza_san_notes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.stevdza_san_notes.R
import com.example.stevdza_san_notes.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NotesViewModel
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBarWithNavController(notesNavHostFragment.findNavController())
        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.notesNavHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}