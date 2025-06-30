package com.taller.gestordeherramientas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.taller.gestordeherramientas.database.entities.Tool
import com.taller.gestordeherramientas.database.daos.ToolDao

@Database(entities = [Tool::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toolDao(): ToolDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gestor_herramientas_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
