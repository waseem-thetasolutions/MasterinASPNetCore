package com.theta.masterinaspnetcore.db

import androidx.paging.DataSource
import androidx.room.*
import com.theta.masterinaspnetcore.model.Category

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cheese: List<Category>)

    @Query("SELECT * FROM category ORDER BY name COLLATE NOCASE ASC")
    fun getAll(): DataSource.Factory<Int, Category>

    @Query("DELETE FROM category")
    fun deleteAll()
}