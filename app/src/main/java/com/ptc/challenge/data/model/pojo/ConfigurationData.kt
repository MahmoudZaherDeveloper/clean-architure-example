package com.ptc.challenge.data.model.pojo


data class ConfigurationData(
    val currency: Currency,
    val languages: List<Language>,
    val support: SupportInfo
)