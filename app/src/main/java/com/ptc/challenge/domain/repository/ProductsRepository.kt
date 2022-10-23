package com.ptc.challenge.domain.repository


import com.ptc.challenge.data.model.pojo.Configurations
import com.ptc.challenge.data.model.pojo.Currency
import com.ptc.challenge.data.model.pojo.ProductDetails
import com.ptc.challenge.data.model.pojo.Response
import com.ptc.challenge.data.remote.ApiResponse
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getConfigurations(): Flow<ApiResponse<Configurations>>
    suspend fun getCurrencyConfigurations(): Currency
    suspend fun getProducts(pageIndex: Int): Flow<ApiResponse<Response>>
    suspend fun getProductDetails(productId: Int): Flow<ApiResponse<ProductDetails>>
    suspend fun getSearchResult(query: String, productId: Int): Flow<ApiResponse<Response>>
}