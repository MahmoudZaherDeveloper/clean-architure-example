package com.ptc.challenge.data.model.pojo


import com.google.gson.annotations.SerializedName

data class Session(
    val expire: Any,
    val id: String,
    @SerializedName("YII_CSRF_TOKEN")
    val yIICSRFTOKEN: String
)