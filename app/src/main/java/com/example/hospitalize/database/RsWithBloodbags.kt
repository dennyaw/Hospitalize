package com.example.hospitalize.database

import androidx.room.Embedded
import androidx.room.Relation

data class RsWithBloodbags (
    @Embedded val rs: RsEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "rs_id"
    )
    val bloodbags: List<BloodbagEntity>
)