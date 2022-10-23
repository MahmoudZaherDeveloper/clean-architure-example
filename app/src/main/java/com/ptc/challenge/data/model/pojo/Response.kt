package com.ptc.challenge.data.model.pojo

import com.ptc.challenge.data.model.pojo.Products


data class Response(
    val metadata: Products,
    val success: Boolean
)