package com.example.posts

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts")
    fun getPosts():Call<List<Posts>>

    @GET("/posts/{postId}")
    fun getPostById(@Path("postId") postId:Int):Call<Posts>

    @GET("/posts/{postId}/comments")
    fun getComments(@Path("postId")commentsId:Int):Call<List<Comment>>


}
