package com.ptc.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptc.challenge.data.model.pojo.ConfigurationData
import com.ptc.challenge.data.remote.ApiResponse
import com.ptc.challenge.domain.interactor.ProductsInteractor
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val productsInteractor = ProductsInteractor()
    val configurations = MutableLiveData<ConfigurationData>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        getAppConfigurations()
    }

    private fun getAppConfigurations() {
        viewModelScope.launch {
            productsInteractor.getConfigurations().catch {
                loading.value = false
                loadError.value = true
            }.collectLatest { apiResponse ->
                if (apiResponse is ApiResponse.Success) {
                    loading.value = false
                    configurations.value = apiResponse.data.metadata
                } else if (apiResponse is ApiResponse.Error) {
                    loading.value = false
                    loadError.value = true
                }
            }

        }
    }
}