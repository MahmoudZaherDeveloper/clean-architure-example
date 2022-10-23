package com.ptc.challenge.data.remote

import com.ptc.challenge.data.model.pojo.Configurations
import com.ptc.challenge.data.model.pojo.ProductDetails
import com.ptc.challenge.data.model.pojo.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {
    @GET("configurations/")
    suspend fun getConfigurations(): retrofit2.Response<Configurations>

    @GET("search/phone/page/{pageIndex}/")
    suspend fun getProducts(@Path("pageIndex") pageIndex: Int): retrofit2.Response<Response>

    @GET("product/{id}/")
    suspend fun getProductDetails(@Path("id") productId: Int): retrofit2.Response<ProductDetails>

    @GET("search/{query}/page/{id}/")
    suspend fun getSearchResult(
        @Path("query") query: String,
        @Path("id") productId: Int
    ): retrofit2.Response<Response>
}