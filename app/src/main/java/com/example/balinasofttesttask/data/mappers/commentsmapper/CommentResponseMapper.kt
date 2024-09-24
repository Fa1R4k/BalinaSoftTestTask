package com.example.balinasofttesttask.data.mappers.commentsmapper

import com.example.balinasofttesttask.data.models.comment.CommentResponse
import com.example.balinasofttesttask.domain.model.CommentOutData
import javax.inject.Inject

class CommentResponseMapper @Inject constructor() {

    operator fun invoke(
        response: CommentResponse
    ) : CommentOutData = with(response) {
        return CommentOutData(
            id = id ?: 0,
            date = date.toString().orEmpty(),
            text = text.orEmpty()
        )
    }
}