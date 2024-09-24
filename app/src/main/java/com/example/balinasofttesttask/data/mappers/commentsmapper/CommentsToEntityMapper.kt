package com.example.balinasofttesttask.data.mappers.commentsmapper

import com.example.balinasofttesttask.data.database.CommentEntity
import com.example.balinasofttesttask.domain.model.CommentOutData
import javax.inject.Inject

class CommentsToEntityMapper @Inject constructor() {

    operator fun invoke(
        comment: CommentOutData
    ): CommentEntity = with(comment) {
        return CommentEntity(
            id = id,
            date = date.toLong(),
            text = text
        )
    }
}