package com.ptc.challenge.data.model.pojo


import com.google.gson.annotations.SerializedName

data class Language(
    val code: String,
    val default: Boolean,
    val name: String
)