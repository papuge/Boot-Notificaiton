package com.example.bootnotification.presentation.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bootnotification.R
import com.example.bootnotification.databinding.ActivityMainBinding
import com.example.bootnotification.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateBootEventTextView()

        // TODO: Setup UI to allow user to modify settings
    }

    private fun updateBootEventTextView() {

    }
}
