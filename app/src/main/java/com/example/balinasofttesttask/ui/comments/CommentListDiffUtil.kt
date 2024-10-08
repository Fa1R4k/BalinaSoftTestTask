package com.example.balinasofttesttask.ui.comments

import androidx.recyclerview.widget.DiffUtil
import com.example.balinasofttesttask.domain.model.CommentOutData
import com.example.balinasofttesttask.domain.model.ImageOutData

class CommentListDiffUtil : DiffUtil.ItemCallback<CommentOutData>() {

    override fun areItemsTheSame(oldItem: CommentOutData, newItem: CommentOutData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CommentOutData, newItem: CommentOutData): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: CommentOutData, newItem: CommentOutData): Any? {
        if (oldItem != newItem) {
            return newItem
        }
        return super.getChangePayload(oldItem, newItem)
    }
}