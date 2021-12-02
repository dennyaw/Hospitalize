package com.example.hospitalize.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bloodbag")
data class BloodbagEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,

    @ColumnInfo(name = "rs_id")
    val rs_id: Int,

    @ColumnInfo(name = "goldar")
    val goldar: String,

    @ColumnInfo(name = "stok")
    val stok: Int
)