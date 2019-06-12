package com.theta.masterinaspnetcore.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class PostListResult (
    val data: LiveData<PagedList<Post>>,
    val networkErrors: LiveData<String>
)