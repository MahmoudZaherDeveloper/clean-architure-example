package com.ptc.challenge.data.model.pojo


import com.google.gson.annotations.SerializedName

data class Rating(
    val average: Int,
    @SerializedName("ratings_total")
    val ratingsTotal: Int
)