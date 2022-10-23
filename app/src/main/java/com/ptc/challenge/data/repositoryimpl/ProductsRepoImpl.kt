package com.ptc.challenge.data.repositoryimpl

import com.ptc.challenge.App
import com.ptc.challenge.data.local.room.ProductDAO
import com.ptc.challenge.data.local.room.ProductDatabase
import com.ptc.challenge.data.model.pojo.Configurations
import com.ptc.challenge.data.model.pojo.Currency
import com.ptc.challenge.data.model.pojo.ProductDetails
import com.ptc.challenge.data.model.pojo.Response
import com.ptc.challenge.data.remote.APIClient
import com.ptc.challenge.data.remote.ApiResponse
import com.ptc.challenge.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class ProductsRepoImpl(private val remoteDataSource: APIClient = APIClient(), private val localDataSource : ProductDAO = ProductDatabase(App.instance).dao()) : ProductsRepository {
    override suspend fun getConfigurations(): Flow<ApiResponse<Configurations>> {
        return remoteDataSource.getConfigurations()
    }

    override suspend fun getCurrencyConfigurations(): Currency {
        return localDataSource.getAllItems()
    }

    override suspend fun getProducts(pageIndex: Int): Flow<ApiResponse<Response>> {
        return remoteDataSource.getProducts(pageIndex)
    }

    override suspend fun getProductDetails(productId: Int): Flow<ApiResponse<ProductDetails>> {
        return remoteDataSource.getProductDetails(productId)
    }

    override suspend fun getSearchResult(query: String, productId: Int): Flow<ApiResponse<Response>> {
        return remoteDataSource.getSearchResult(query, productId)
    }

}