package com.example.posts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.databinding.PostListItemBinding

class PostAdapter(var postList: List<Posts>):RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
      var binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false
      )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
       var currentPost = postList.get(position)
           with(holder.binding){
               postId.text= currentPost.userId.toString()
               pId.text= currentPost.id.toString()
               titleId.text = currentPost.title
               bodId.text=currentPost.body
               val context = holder.itemView.context
               holder.binding.cvPost.setOnClickListener{
                   val intent = Intent(context,CommentActivity::class.java)
                   intent.putExtra("POST_ID",currentPost.id)
                   context.startActivity(intent)
               }

           }
    }

    override fun getItemCount(): Int {
       return postList.size
    }
}

class PostViewHolder(var binding: PostListItemBinding):RecyclerView.ViewHolder(binding.root)