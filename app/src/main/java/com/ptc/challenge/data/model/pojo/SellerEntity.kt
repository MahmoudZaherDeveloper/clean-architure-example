package com.ptc.challenge.data.model.pojo


import com.google.gson.annotations.SerializedName

data class SellerEntity(
    @SerializedName("delivery_time")
    val deliveryTime: String,
    val id: Int,
    val name: String
)