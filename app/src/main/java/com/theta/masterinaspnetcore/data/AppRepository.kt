package com.theta.masterinaspnetcore.data

import android.content.Context
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.theta.masterinaspnetcore.api.DevApi
import com.theta.masterinaspnetcore.api.RetroClient
import com.theta.masterinaspnetcore.db.AppDatabase
import com.theta.masterinaspnetcore.model.*
import java.util.concurrent.Executors

class AppRepository {
    private val mDb : AppDatabase
    private val mApiService: DevApi
    private val executor  = Executors.newSingleThreadExecutor()

    private val pagedListConfig: PagedList.Config

    private constructor(context : Context){
        mDb = AppDatabase.getDatabase(context)
        mApiService = RetroClient.getDevApi()

        pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(DATABASE_PAGE_SIZE)
            .build()
    }

    fun getAllCategories() : CategoriesListResult {
        // Get data source factory from the local cache
        val dataSourceFactory = mDb.categoryDao().getAll()
        val boundaryCallback = CategoriesBoundaryCallback(mApiService, this)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list

        val data = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
            .setBoundaryCallback(boundaryCallback)
            .build()

        // Get the network errors exposed by the boundary callback
        return CategoriesListResult(data, networkErrors)
    }

    fun insertAllCategories(categories: List<Category>, insertFinished: () -> Unit){
        executor.execute {
            mDb.categoryDao().insertAll(categories)
            insertFinished()
        }
    }

//    fun getPostsByCategory(): PostListResult {
//        val dataSourceFactory = mDb.postDao().getAll()
//        val boundaryCallback = PostsBoundaryCallback(mApiService, this)
//        val networkErrors = boundaryCallback.networkErrors
//
//        val data = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
//            .setBoundaryCallback(boundaryCallback)
//            .build()
//
//        return PostListResult(data,networkErrors)
//    }

    fun getPostsByCategory(categoryId: Int): PostListResult {
        val dataSourceFactory = mDb.postDao().getPostsByCategory(categoryId)
        val boundaryCallback = PostsByCategoryBoundaryCallback(mApiService, this,categoryId)
        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return PostListResult(data,networkErrors)
    }

    fun insertAllPosts(posts: List<Post>, insertFinished: () -> Unit) {
        executor.execute {
            mDb.postDao().insertAll(posts)
            insertFinished()
        }
    }

    fun getPostById(id: Int): Post {
        return mDb.postDao().getById(id)
    }

    fun savePost(post: Post) {
        mDb.postDao().insert(post)
    }

    fun deleteAllCategories() {
        executor.execute {
            mDb.categoryDao().deleteAll()
        }
    }

    fun getNewsUpdate(): RetrofitLiveData<List<News>> = RetrofitLiveData(mApiService.getAllNews())

    companion object {
        private lateinit var sInstance : AppRepository
        private const val DATABASE_PAGE_SIZE = 20
        private const val INITIAL_LOAD_SIZE_HINT = 30

        @JvmStatic
        fun getInstance(context: Context) : AppRepository {
            if (!Companion::sInstance.isInitialized){
                sInstance = AppRepository(context)
            }
            return sInstance
        }

    }

}