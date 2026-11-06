package com.example.dsy1105_007d_2026.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [OportunidadEntity::class], version = 1)
abstract class OportunidadDatabase : RoomDatabase() {

    abstract fun oportunidadDao() : OportunidadDao

    companion object{

        @Volatile
        private var INSTANCE : OportunidadDatabase? = null

        fun getDatabase(context: Context) : OportunidadDatabase{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    OportunidadDatabase::class.java,
                    "oportunidad_database"
                ).build().also {INSTANCE = it}
            }
        }
    }
}