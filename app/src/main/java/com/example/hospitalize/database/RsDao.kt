package com.example.hospitalize.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface RsDao {

    @Query("SELECT * FROM rs ORDER BY id DESC")
    fun getAllRsInfo(): List<RsEntity>?

    @Update
    fun updateRs(user: RsEntity)

    @Delete
    fun deleteRs(user: RsEntity)

    @Insert
    fun insertRs(user: RsEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertBloodbag(bloodbag: BloodbagEntity)

    @Transaction
    @Query("SELECT * FROM rs WHERE id = :id")
    suspend fun getRsWithBloodbags(id: Int): List<RsWithBloodbags>
}