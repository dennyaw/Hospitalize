package com.example.hospitalize.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RsEntity::class, BloodbagEntity::class], version = 2, exportSchema = true)
abstract class RoomAppDb: RoomDatabase() {
    abstract fun rsDao(): RsDao?

    companion object {
        @Volatile
        private var INSTANCE: RoomAppDb?= null

        @JvmStatic
        fun getAppDatabase(context: Context): RoomAppDb? {

            if(INSTANCE == null) {
                Log.d("CREATION", "Database executed")
                INSTANCE = Room.databaseBuilder<RoomAppDb>(
                    context.applicationContext, RoomAppDb::class.java, "AppDB"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("database/rs.db")
                    .build()
            }
            return INSTANCE
        }
    }
}