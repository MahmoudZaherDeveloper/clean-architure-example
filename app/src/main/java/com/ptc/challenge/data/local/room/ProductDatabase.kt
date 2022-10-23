package com.ptc.challenge.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ptc.challenge.data.model.pojo.Currency

@Database(entities = [Currency::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun dao(): ProductDAO

    companion object {
        @Volatile
        private var instance: ProductDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java,
            "products_database"
        ).build()
    }
}
