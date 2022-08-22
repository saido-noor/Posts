package com.example.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.databinding.ActivityCommentBinding
import com.example.posts.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST
import kotlin.math.log

class CommentActivity : AppCompatActivity() {
    var postId = 0
    var comment = 0
    lateinit var binding: ActivityCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        fetchPostById()
        obtainCommentId()
        fetchComment()
        fetchPostById()

    }

    fun obtainPostId(){
        postId = intent.extras?.getInt("POST_ID")?:0
    }

    fun obtainCommentId(){
        var extras = intent
        postId= extras.getIntExtra("POST_ID",0)
    }

    fun fetchPostById(){
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getPostById(postId)
        request.enqueue( object: Callback<Posts>{
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
              if (response .isSuccessful) {
                  var post = response.body()
                  binding.tvTittle.text = post?.title
                  binding.tvBody.text = post?.title
              }
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
              Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun fetchComment() {
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getComments(postId)

        request.enqueue(object : Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    var comments = response.body()?: emptyList()
                    displayComment(comments)

                }

            }


            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun displayComment(comments: List<Comment>){
        var adapter = CommentAdapter(comments)
        binding.rcvComment.layoutManager = LinearLayoutManager(this)
        binding.rcvComment.adapter = adapter
    }



//    fun setupToolbar(){
//        setSupportActionBar(binding.toolbarComments)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//    }


}


