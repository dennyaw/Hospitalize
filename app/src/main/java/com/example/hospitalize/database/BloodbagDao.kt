package com.example.hospitalize.database

import androidx.room.*

interface BloodbagDao {
    @Query("SELECT * FROM bloodbag ORDER BY id DESC")
    fun getAllRsInfo(): List<RsEntity>?

    @Update
    fun updateRs(user: RsEntity)

    @Delete
    fun deleteRs(user: RsEntity)

    @Insert
    fun insertRs(user: RsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBloodbag(bloodbag: BloodbagEntity)

    @Transaction
    @Query("SELECT * FROM rs WHERE id = :id")
    suspend fun getRsWithBloodbags(id: Int): List<RsWithBloodbags>
}