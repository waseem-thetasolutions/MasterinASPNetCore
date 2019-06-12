package com.theta.masterinaspnetcore.api

import android.content.ContentValues.TAG
import android.util.Log
import com.theta.masterinaspnetcore.model.Category
import com.theta.masterinaspnetcore.model.PostMedia
import com.theta.masterinaspnetcore.model.News
import com.theta.masterinaspnetcore.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

fun getCategories(
    service: DevApi,
    page: Int,
    itemsPerPage: Int,
    onSuccess: (repos: List<Category>) -> Unit,
    onError: (error: String) -> Unit
) {

    service.getAllCategories(page,itemsPerPage).enqueue(
        object : Callback<List<Category>> {
            override fun onFailure(call: Call<List<Category>>?, t: Throwable) {
                Log.d(TAG, "fail to get data")
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                call: Call<List<Category>>?,
                response: Response<List<Category>>
            ) {
                Log.d(TAG, "got a response $response")
                if (response.isSuccessful) {
                    val categories = response.body() ?: emptyList()
                    onSuccess(categories)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}

fun getPosts(service: DevApi,
             page: Int,
             itemsPerPage: Int,
             onSuccess: (repos: List<Post>) -> Unit,
             onError: (error: String) -> Unit){
    service.getAllPosts(page,itemsPerPage).enqueue(
        object : Callback<List<Post>>{
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d(TAG, "fail to get data")
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val data = response.body() ?: emptyList()
                    onSuccess(data)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }

        }
    )
}

fun getPostsByCategory(service: DevApi,
                       categoryId: Int,
             page: Int,
             itemsPerPage: Int,

             onSuccess: (repos: List<Post>) -> Unit,
             onError: (error: String) -> Unit){
    service.getPostsByCategory(categoryId,page,itemsPerPage).enqueue(
        object : Callback<List<Post>>{
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d(TAG, "fail to get data")
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val data = response.body() ?: emptyList()
                    onSuccess(data)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }

        }
    )
}


interface DevApi {
    @GET("categories")
    fun getAllCategories(@Query("page") page: Int,@Query("per_page") itemsPerPage: Int) : Call<List<Category>>

    @GET("posts")
    fun getAllPosts(@Query("page") page: Int,@Query("per_page") itemsPerPage: Int): Call<List<Post>>

    @GET("posts")
    fun getPostsByCategory(@Query("categories") categoryId: Int, @Query("page") page: Int,@Query("per_page") itemsPerPage: Int): Call<List<Post>>

    @GET("news")
    fun getAllNews(@Query("page") page: Int,@Query("per_page") itemsPerPage: Int): Call<List<Post>>

    @GET("news")
    fun getAllNews(): Call<List<News>>

    @GET("media/{id}")
    fun getMedia(@Path("id") mediaId: Int) : Call<PostMedia>
}