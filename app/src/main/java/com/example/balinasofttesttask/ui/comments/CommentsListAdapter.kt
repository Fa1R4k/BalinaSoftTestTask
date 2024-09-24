package com.example.balinasofttesttask.ui.comments

import com.example.balinasofttesttask.databinding.ItemPhotoBinding
import com.example.balinasofttesttask.domain.model.ImageOutData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.balinasofttesttask.databinding.ItemCommentBinding
import com.example.balinasofttesttask.domain.model.CommentOutData

class CommentsListAdapter(
    private val itemClickListener: RecyclerViewCommentItemClickListener
) : PagingDataAdapter<CommentOutData, CommentListHolder>(CommentListDiffUtil()) {

    override fun onBindViewHolder(holder: CommentListHolder, position: Int) {
        val itemCommentBinding = getItem(position) ?: return
        itemCommentBinding.let {
            holder.onBind(itemCommentBinding)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = ItemCommentBinding.inflate(inflater)
        return CommentListHolder(item, itemClickListener)
    }

    override fun onBindViewHolder(
        holder: CommentListHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val item = payloads[0] as CommentOutData
            holder.onBind(item)
        }

    }
}