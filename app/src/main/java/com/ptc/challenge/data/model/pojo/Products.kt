package com.ptc.challenge.data.model.pojo


import com.google.gson.annotations.SerializedName
import com.ptc.challenge.data.model.pojo.ProductData

data class Products(
    val results: List<ProductData>,
    val sort: String,
    val title: String,
    @SerializedName("total_products")
    val totalProducts: Int
)