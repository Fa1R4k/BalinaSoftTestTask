package com.example.balinasofttesttask.data.mappers.commentsmapper

import com.example.balinasofttesttask.data.models.comment.CommentResponse
import com.example.balinasofttesttask.domain.model.CommentOutData
import javax.inject.Inject

class CommentsResponseMapper @Inject constructor() {

    operator fun invoke(
        comment: CommentOutData
    ) : CommentResponse = with(comment) {
        return CommentResponse(
            id = id,
            date = date.toLong(),
            text = text
        )
    }
}