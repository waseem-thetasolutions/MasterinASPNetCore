package com.theta.masterinaspnetcore.ui.topic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.theta.masterinaspnetcore.data.AppRepository
import com.theta.masterinaspnetcore.model.PostListResult
import java.util.concurrent.Executors

class TopicListViewModel(app: Application) : AndroidViewModel(app) {
    private val mAppRepository : AppRepository = AppRepository.getInstance(app.applicationContext)

    fun getPostsByCategory(categoryId: Int) : PostListResult{
        return mAppRepository.getPostsByCategory(categoryId)
    }


}
