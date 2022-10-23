package com.ptc.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptc.challenge.data.model.pojo.ProductDetailsData
import com.ptc.challenge.data.remote.ApiResponse
import com.ptc.challenge.domain.interactor.ProductsInteractor
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val productsInteractor = ProductsInteractor()
    val productDetails = MutableLiveData<ProductDetailsData>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun fetchProductDetailsFromRemote(productId: Int) {
        viewModelScope.launch {
            productsInteractor.getProductDetails(productId).catch {
                loading.value = false
                loadError.value = true
            }.collectLatest { apiResponse ->
                if (apiResponse is ApiResponse.Success) {
                    loading.value = false
                    productDetails.value = apiResponse.data.metadata
                } else if (apiResponse is ApiResponse.Error) {
                    loading.value = false
                    loadError.value = true
                }
            }

        }
    }
}