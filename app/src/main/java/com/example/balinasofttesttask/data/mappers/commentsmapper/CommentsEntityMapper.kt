package com.example.balinasofttesttask.data.mappers.commentsmapper

import com.example.balinasofttesttask.data.database.CommentEntity
import com.example.balinasofttesttask.domain.model.CommentOutData

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CommentsEntityMapper @Inject constructor(){

    operator fun invoke(
        entity: CommentEntity
    ): CommentOutData = with(entity) {
        return CommentOutData(
            id = id,
            date = mapTimestampToDate(date),
            text = text
        )
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp)
        return SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(date)
    }
}