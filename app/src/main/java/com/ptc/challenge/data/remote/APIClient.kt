package com.ptc.challenge.data.remote



import com.ptc.challenge.core.constant.BASE_URL
import com.ptc.challenge.data.model.pojo.Configurations
import com.ptc.challenge.data.model.pojo.ProductDetails
import com.ptc.challenge.data.model.pojo.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class APIClient {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIInterface::class.java)

    suspend fun getConfigurations(): Flow<ApiResponse<Configurations>> {
        val response = api.getConfigurations()
        return if (response.code() == 200)
            flowOf(ApiResponse.Success(response.body() as Configurations))
        else if (response.code() == 404)
            flow { emit(ApiResponse.Error(throwable = Throwable("not found!"))) }
        else
            flow { emit(ApiResponse.Error(throwable = Throwable("some thing went wrong!"))) }
    }

    /* suspend fun getConfigurationsFromDb(): Currency {
         return
     }*/

    suspend fun getProducts(pageIndex: Int): Flow<ApiResponse<Response>> {
        val response = api.getProducts(pageIndex)
        return if (response.code() == 200)
            flowOf(ApiResponse.Success(response.body() as Response))
        else if (response.code() == 404)
            flow { emit(ApiResponse.Error(throwable = Throwable("no more data found!"))) }
        else
            flow { emit(ApiResponse.Error(throwable = Throwable("some thing went wrong!"))) }
    }

    suspend fun getSearchResult(query: String, pageIndex: Int): Flow<ApiResponse<Response>> {
        val response = api.getSearchResult(query, pageIndex)
        return if (response.code() == 200)
            flowOf(ApiResponse.Success(response.body() as Response))
        else if (response.code() == 404)
            flow { emit(ApiResponse.Error(throwable = Throwable("no more data found!"))) }
        else
            flow { emit(ApiResponse.Error(throwable = Throwable("some thing went wrong!"))) }
    }

    suspend fun getProductDetails(productId: Int): Flow<ApiResponse<ProductDetails>> {
        val response = api.getProductDetails(productId)
        return if (response.code() == 200) {
            flow {
                emit(ApiResponse.Success(response.body() as ProductDetails))
            }
        } else flow {
            emit(ApiResponse.Error(throwable = Throwable("Some thing went wrong!")))
        }
    }

}

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error<T>(val throwable: Throwable) : ApiResponse<T>()
}
