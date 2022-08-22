package com.example.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.databinding.ActivityCommentBinding
import com.example.posts.databinding.CommentListItemsBinding

class CommentAdapter(var commentList: List<Comment>):RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var binding = CommentListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var currentComment = commentList.get(position)
        with(holder.binding){
            tvPostid.text=currentComment.postId.toString()
            tvId.text= currentComment.id.toString()
            tvName.text=currentComment.name
            tvEmail.text=currentComment.email
            tvBodyComment.text=currentComment.body


        }
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
}

class CommentViewHolder(var binding: CommentListItemsBinding):RecyclerView.ViewHolder(binding.root)