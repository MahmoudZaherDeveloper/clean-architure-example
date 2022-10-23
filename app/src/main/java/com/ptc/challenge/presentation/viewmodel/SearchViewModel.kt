package com.ptc.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptc.challenge.data.model.pojo.Response
import com.ptc.challenge.data.remote.ApiResponse
import com.ptc.challenge.domain.interactor.ProductsInteractor
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val productsInteractor = ProductsInteractor()
    val products = MutableLiveData<Response>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private var canLoading = true
    private var pageNumber = 1

    fun loadMore() {
        if (canLoading) {
            // searchForProduct(++pageNumber)
        }
    }

    fun searchForProduct(query: String, pageIndex: Int) {
        loading.value = true
        viewModelScope.launch {
            productsInteractor.getSearchResult(query, pageIndex).catch {
                loading.value = false
                loadError.value = true
            }.collectLatest { apiResponse ->
                if (apiResponse is ApiResponse.Success) {
                    loading.value = false
                    products.value = apiResponse.data
                    //  canLoading = apiResponse.data.metadata.results.isNotEmpty()
                } else if (apiResponse is ApiResponse.Error) {
                    loading.value = false
                    loadError.value = true
                }

            }
        }
    }
}