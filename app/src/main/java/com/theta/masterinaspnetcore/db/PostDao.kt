package com.theta.masterinaspnetcore.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.theta.masterinaspnetcore.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM post ORDER BY date COLLATE NOCASE ASC")
    fun getAll(): DataSource.Factory<Int, Post>

    @Query("SELECT * FROM post WHERE id = :id")
    fun getById(id: Int): Post

    @Query("SELECT * FROM post WHERE categoryId = :categoryId")
    fun getPostsByCategory(categoryId: Int): DataSource.Factory<Int, Post>

    @Query("SELECT * FROM post WHERE type = :type")
    fun getPostByType(type: String): DataSource.Factory<Int, Post>
}