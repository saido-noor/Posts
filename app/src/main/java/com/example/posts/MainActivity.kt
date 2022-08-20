package com.example.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.databinding.ActivityMainBinding
import com.example.posts.databinding.PostListItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPOsts()
    }
    fun fetchPOsts(){
        val apiClient  = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.getPosts()
        request.enqueue(object: Callback<List<Posts>>{
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (response.isSuccessful){
                    var posts = response.body()
                    Toast.makeText(baseContext, "fetched ${posts!!.size} posts ", Toast.LENGTH_LONG).show()
                   binding.rvView.layoutManager= LinearLayoutManager(baseContext)
                   binding.rvView.adapter=PostAdapter(posts)
                }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
//                Toast.makeText(Toas)
            }


        })

    }
}