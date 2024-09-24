package com.example.balinasofttesttask.data.mappers.commentsmapper

import com.example.balinasofttesttask.data.database.CommentEntity
import com.example.balinasofttesttask.domain.model.CommentOutData
import javax.inject.Inject

class CommentsListToEntityMapper @Inject constructor() {

    operator fun invoke(
        comments: List<CommentOutData>
    ): List<CommentEntity> = comments.map {
        CommentEntity(
            id = it.id,
            date = it.date.toLong(),
            text = it.text
        )
    }
}