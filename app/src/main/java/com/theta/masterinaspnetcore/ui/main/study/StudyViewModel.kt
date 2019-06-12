package com.theta.masterinaspnetcore.ui.main.study

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.theta.masterinaspnetcore.data.AppRepository
import com.theta.masterinaspnetcore.model.CategoriesListResult

class StudyViewModel(app: Application) : AndroidViewModel(app) {

    private val mAppRepository : AppRepository = AppRepository.getInstance(app.applicationContext)
    val mCategoriesListResult: CategoriesListResult

    init {
        mCategoriesListResult = mAppRepository.getAllCategories()

    }

}