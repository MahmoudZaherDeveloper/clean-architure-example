package com.ptc.challenge.data.model.pojo


import com.google.gson.annotations.SerializedName

data class ProductDetailsData(
    val brand: String,
    @SerializedName("image_list")
    val imageList: List<String>,
    @SerializedName("max_saving_percentage")
    val maxSavingPercentage: Int,
    val name: String,
    val price: Int,
    val rating: Rating,
    @SerializedName("seller_entity")
    val sellerEntity: SellerEntity,
    val sku: String,
    @SerializedName("special_price")
    val specialPrice: Int,
    val summary: Summary
)