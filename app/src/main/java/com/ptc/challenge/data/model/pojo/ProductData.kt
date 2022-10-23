package com.ptc.challenge.data.model.pojo


import com.google.gson.annotations.SerializedName

data class ProductData(
    val brand: String,
    val image: String,
    @SerializedName("max_saving_percentage")
    val maxSavingPercentage: Int,
    val name: String,
    val price: Int,
    @SerializedName("rating_average")
    val ratingAverage: Int,
    val sku: String,
    @SerializedName("special_price")
    val specialPrice: Int
)