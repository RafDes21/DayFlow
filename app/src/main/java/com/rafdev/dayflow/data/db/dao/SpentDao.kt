package com.rafdev.dayflow.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafdev.dayflow.data.db.enteties.SpentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpentDao {

    @Query("SELECT * FROM table_spent")
    fun getSpent():Flow<List<SpentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(spent: SpentEntity)

    @Query("DELETE FROM table_spent WHERE id=:id")
   suspend fun deleteById(id:Int)

}