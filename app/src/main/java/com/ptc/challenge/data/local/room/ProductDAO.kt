package com.ptc.challenge.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ptc.challenge.data.model.pojo.Currency


@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItems(vararg obj: Currency)

    @Query("SELECT * FROM currencyEntity")
    suspend fun getAllItems(): Currency

    @Query("SELECT * FROM currencyEntity WHERE currencyId = :id")
    suspend fun getItemByID(id: Int): Currency

    @Query("DELETE FROM currencyEntity")
    suspend fun deleteAllItems()

}