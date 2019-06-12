package com.theta.masterinaspnetcore.ui.main.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.theta.masterinaspnetcore.data.AppRepository
import com.theta.masterinaspnetcore.data.RetrofitLiveData
import com.theta.masterinaspnetcore.model.News
import com.theta.masterinaspnetcore.model.PostListResult
import java.util.concurrent.Executors

class HomeViewModel(app: Application) : AndroidViewModel(app) {
    private val mAppRepository : AppRepository = AppRepository.getInstance(app.applicationContext)
    val mNews: RetrofitLiveData<List<News>>

    init {
        //mPostListResult = mAppRepository.getPostsByCategory()
        mNews = mAppRepository.getNewsUpdate()
    }


}
