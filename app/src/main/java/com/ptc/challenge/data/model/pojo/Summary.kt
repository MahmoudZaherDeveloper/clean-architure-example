package com.ptc.challenge.data.model.pojo


import com.google.gson.annotations.SerializedName

data class Summary(
    val description: String,
    @SerializedName("short_description")
    val shortDescription: String
)