package com.theta.masterinaspnetcore.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.theta.masterinaspnetcore.api.DevApi
import com.theta.masterinaspnetcore.api.getCategories
import com.theta.masterinaspnetcore.api.getPosts
import com.theta.masterinaspnetcore.api.getPostsByCategory
import com.theta.masterinaspnetcore.model.Category
import com.theta.masterinaspnetcore.model.Post

class PostsByCategoryBoundaryCallback(
    private val service: DevApi,
    private val cache: AppRepository,
    private val categoryId: Int
    ) : PagedList.BoundaryCallback<Post>(){

    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        Log.d("RepoBoundaryCallback", "onZeroItemsLoaded")
        requestAndSaveData()
    }

    /**
     * When all items in the database were loaded, we need to query the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: Post) {
        Log.d("RepoBoundaryCallback", "onItemAtEndLoaded")
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        getPostsByCategory(service,categoryId, lastRequestedPage, NETWORK_PAGE_SIZE, { posts ->
            val postList = arrayListOf<Post>()
            for (post in posts){
                post.categoryId = post.categories[0]
                postList.add(post)
            }
            cache.insertAllPosts(postList) {
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}