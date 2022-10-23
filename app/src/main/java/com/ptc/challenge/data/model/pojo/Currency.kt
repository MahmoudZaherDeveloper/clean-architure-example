package com.ptc.challenge.data.model.pojo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currencyEntity")
data class Currency(
    @SerializedName("currency_symbol")
    val currencySymbol: String,
    val decimals: Int,
    @SerializedName("decimals_sep")
    val decimalsSep: String,
    val iso: String,
    val position: Int,
    @SerializedName("thousands_sep")
    val thousandsSep: String
) {
    @PrimaryKey(autoGenerate = true)
    var currencyId: Int = 0
}