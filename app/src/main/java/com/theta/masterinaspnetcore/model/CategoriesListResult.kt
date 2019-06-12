package com.theta.masterinaspnetcore.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class CategoriesListResult (
    val data: LiveData<PagedList<Category>>,
    val networkErrors: LiveData<String>
)