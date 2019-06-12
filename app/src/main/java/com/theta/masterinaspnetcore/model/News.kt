package com.theta.masterinaspnetcore.model

import com.google.gson.annotations.SerializedName

data class News (

    val id: Int,

    val content: Content,

    val date: String,

    @SerializedName("featured_media")
    val featuredMedia: Int,

    val link: String,

    val status: String,

    val title: Title,

    val type: String
    )