package com.kevinolivera.ec.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<Entity> {
    @Insert
    fun insert(entity: Entity)

    @Update
    fun update(entity: Entity) : Int

    @Delete
    fun delete(entity: Entity)
}