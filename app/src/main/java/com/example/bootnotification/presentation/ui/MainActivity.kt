package com.example.bootnotification.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.bootnotification.databinding.ActivityMainBinding
import com.example.bootnotification.domain.model.BootEvent
import com.example.bootnotification.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bootEvents.collectLatest { bootEvents ->
                    updateBootEventTextView(bootEvents)
                }
            }
        }
    }

    private fun updateBootEventTextView(bootEvents: List<BootEvent>) {
        if (bootEvents.isEmpty()) {
            binding.bootEventTextView.text = "No boots detected"
        } else {
            val groupedEvents = bootEvents.groupBy {
                SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.US
                ).format(it.timestamp)
            }
            val bootEventInfo =
                groupedEvents.entries.joinToString("\n") { "${it.key} - ${it.value.size}" }
            binding.bootEventTextView.text = bootEventInfo
        }
    }
}
