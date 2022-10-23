package com.ptc.challenge.domain.interactor

import com.ptc.challenge.data.model.pojo.Configurations
import com.ptc.challenge.data.model.pojo.Currency
import com.ptc.challenge.data.model.pojo.ProductDetails
import com.ptc.challenge.data.model.pojo.Response
import com.ptc.challenge.data.remote.ApiResponse
import com.ptc.challenge.data.repositoryimpl.ProductsRepoImpl
import com.ptc.challenge.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class ProductsInteractor(private val repository: ProductsRepository = ProductsRepoImpl()) {
    suspend fun getConfigurations(): Flow<ApiResponse<Configurations>> {
        return repository.getConfigurations()
    }

    suspend fun getCurrencyConfiguration(): Currency {
        return repository.getCurrencyConfigurations()
    }

    suspend fun getProducts(pageIndex: Int): Flow<ApiResponse<Response>> {
        return repository.getProducts(pageIndex)
    }

    suspend fun getProductDetails(productId: Int): Flow<ApiResponse<ProductDetails>> {
        return repository.getProductDetails(productId)
    }

    suspend fun getSearchResult(query: String, pageIndex: Int): Flow<ApiResponse<Response>> {
        return repository.getSearchResult(query, pageIndex)
    }
}