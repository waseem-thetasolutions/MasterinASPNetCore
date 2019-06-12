package com.theta.masterinaspnetcore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "category")
data class Category(
//    val count: Int,
//    val description: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
//    val link: String,
    val name: String,
//    val parent: Int,
//    val slug: String,
//    val taxonomy: String
    val status: Int = 0,
    val progress: Int = 0
)