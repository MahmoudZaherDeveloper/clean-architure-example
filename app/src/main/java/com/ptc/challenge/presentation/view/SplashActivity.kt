package com.ptc.challenge.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ptc.challenge.databinding.ActivitySplashBinding
import com.ptc.challenge.presentation.viewmodel.SplashViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    lateinit var configurationsViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurationsViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        observeConfigurationsViewModel()
    }

    private fun observeConfigurationsViewModel() {
        configurationsViewModel.configurations.observe(this) { configurationData ->
            configurationData?.let {
                val currency = configurationData.currency
                Log.d("TAG", "observeConfigurationsViewModel: $currency")
                Intent(this@SplashActivity, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
        configurationsViewModel.loadError.observe(this) { error ->
            error?.let {
                //errorTextView.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        configurationsViewModel.loading.observe(this) { loading ->
            loading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {

                }
            }
        }
    }
}