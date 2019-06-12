package com.theta.masterinaspnetcore.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post")
data class Post(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val categories: List<Int>,

    var categoryId: Int = categories[0],

    @ColumnInfo
    val content: Content,

    val date: String,

    @SerializedName("featured_media")
    val featuredMedia: Int,

    val link: String,

    val status: String,

    @ColumnInfo
    val title: Title,

    val type: String,

    var isRead: Boolean = false
)