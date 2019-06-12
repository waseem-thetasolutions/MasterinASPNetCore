package com.theta.masterinaspnetcore.model

import com.google.gson.annotations.SerializedName

data class PostMedia (
    val id: Int,

    @SerializedName("source_url")
    val source: String
)