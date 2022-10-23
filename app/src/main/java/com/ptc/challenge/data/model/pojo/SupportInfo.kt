package com.ptc.challenge.data.model.pojo


import com.google.gson.annotations.SerializedName

data class SupportInfo(
    @SerializedName("call_to_order_enabled")
    val callToOrderEnabled: Boolean,
    @SerializedName("cs_email")
    val csEmail: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)