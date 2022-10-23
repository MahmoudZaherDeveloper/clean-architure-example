package com.ptc.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptc.challenge.App
import com.ptc.challenge.core.utils.SharedPreferencesHelper
import com.ptc.challenge.data.model.pojo.Currency
import com.ptc.challenge.data.model.pojo.ProductData
import com.ptc.challenge.data.remote.ApiResponse
import com.ptc.challenge.domain.interactor.ProductsInteractor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val productsInteractor = ProductsInteractor()
    val products = MutableLiveData<List<ProductData>>()
    private var prefHelper = SharedPreferencesHelper(App.instance)
    private var refreshTime = 30 * 60 * 1000 * 1000 * 1000L
    val error = MutableLiveData<Throwable>()
    val loading = MutableLiveData<Boolean>()
    val currency = MutableLiveData<Currency>()
    private var canLoading = true
    private var pageNumber = 1

    init {
        fetchProductsFromRemote(pageNumber)
        getCurrency()
    }

    private fun getCurrency() {
        val updateTime = prefHelper.getUpdateTime()
        viewModelScope.launch {
            if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime)
                currency.value = productsInteractor.getCurrencyConfiguration()
            else {
                productsInteractor.getConfigurations().collectLatest { apiResponse ->
                    if (apiResponse is ApiResponse.Success) {
                        currency.value = apiResponse.data.metadata.currency
                    } else if (apiResponse is ApiResponse.Error) {
                        error.value = apiResponse.throwable
                    }
                }
            }

        }
    }

    fun loadMore() {
        if (canLoading) {
            pageNumber++
            fetchProductsFromRemote(pageNumber)
        }
    }

    fun fetchProductsFromRemote(pageIndex: Int) {
        loading.value = true
        canLoading = false
        viewModelScope.launch {
            productsInteractor.getProducts(pageIndex).collectLatest { apiResponse ->
                if (apiResponse is ApiResponse.Success) {
                    loading.value = false
                    canLoading = true
                    products.value = apiResponse.data.metadata.results
                } else if (apiResponse is ApiResponse.Error) {
                    loading.value = false
                    error.value = apiResponse.throwable
                }

            }
        }
    }

    fun getCurrencyConfiguration() {
        viewModelScope.launch {
            currency.value = productsInteractor.getCurrencyConfiguration()
        }
    }
}