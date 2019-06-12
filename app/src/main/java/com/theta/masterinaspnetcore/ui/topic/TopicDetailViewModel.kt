package com.theta.masterinaspnetcore.ui.topic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.theta.masterinaspnetcore.data.AppRepository
import com.theta.masterinaspnetcore.model.Post
import java.util.concurrent.Executors

class TopicDetailViewModel(app: Application) : AndroidViewModel(app) {

    private val mAppRepository : AppRepository = AppRepository.getInstance(app.applicationContext)
    val mPost = MutableLiveData<Post>()
    private val mExecutor = Executors.newSingleThreadExecutor()

    fun getPostById(id: Int){
        mExecutor.execute {
            val post = mAppRepository.getPostById(id)
            mPost.postValue(post)
        }
    }

    fun savePost(post: Post) {
        mExecutor.execute {
            mAppRepository.savePost(post)
        }
    }
}
