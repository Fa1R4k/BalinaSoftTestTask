package com.example.balinasofttesttask.data.mappers.commentsmapper

import com.example.balinasofttesttask.domain.model.CommentInData
import com.example.balinasofttesttask.data.models.comment.AddCommentRequest
import javax.inject.Inject

class CommentInResponseMapper @Inject constructor() {

    operator fun invoke(
        commentIn: CommentInData
    ) : AddCommentRequest {
        return AddCommentRequest(
            text = commentIn.text
        )
    }
}