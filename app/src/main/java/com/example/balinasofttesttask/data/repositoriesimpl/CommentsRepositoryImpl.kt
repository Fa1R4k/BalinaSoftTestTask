package com.example.balinasofttesttask.data.repositoriesimpl

import com.example.balinasofttesttask.data.network.service.CommentService
import com.example.balinasofttesttask.data.mappers.commentsmapper.CommentInResponseMapper
import com.example.balinasofttesttask.data.mappers.commentsmapper.CommentResponseMapper
import com.example.balinasofttesttask.data.mappers.commentsmapper.CommentsEntityMapper
import com.example.balinasofttesttask.data.mappers.commentsmapper.CommentsListToEntityMapper
import com.example.balinasofttesttask.data.mappers.commentsmapper.CommentsToEntityMapper
import com.example.balinasofttesttask.data.models.comment.CommentResponse
import com.example.balinasofttesttask.data.source.CommentDataSource
import com.example.balinasofttesttask.domain.model.CommentInData
import com.example.balinasofttesttask.domain.model.CommentOutData
import com.example.balinasofttesttask.domain.repositories.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.IllegalStateException
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val database: CommentDataSource,
    private val commentService: CommentService,
    private val commentResponseMapper: CommentResponseMapper,
    private val commentsEntityMapper: CommentsEntityMapper,
    private val commentInResponseMapper: CommentInResponseMapper,
    private val commentsToEntityMapper: CommentsToEntityMapper,
    private val commentsListToEntityMapper: CommentsListToEntityMapper
) : CommentRepository {

    override suspend fun getComments(imageId: Int, page: Int): List<CommentOutData> {
        val response = commentService.getComments(imageId, page)
        val commentsResponse = response.body()
        val comments = commentsResponse?.data
        return comments?.map { commentResponseMapper(it) }
            ?: listOf()
    }

    override suspend fun deleteComment(commentId: Int, imageId: Int): Result<Unit> {
        return try {
            val response = commentService.deleteComment(imageId, commentId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete image"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addComment(commentIn: String, imageId: Int): CommentOutData =
        withContext(Dispatchers.IO) {
            val response = commentInResponseMapper(CommentInData(commentIn))
            val comment = commentService.addComment(response, imageId)
            commentResponseMapper(comment.body()?.data ?: CommentResponse())
        }

    override suspend fun getCommentsFromDb(): List<CommentOutData> = withContext(Dispatchers.IO) {
        database.getCommentsFromDB().map { commentsEntityMapper(it) }
    }

    override suspend fun insertComment(comment: CommentOutData) = withContext(Dispatchers.IO) {
        database.insertComment(commentsToEntityMapper(comment))
    }

    override suspend fun insertComments(comment: List<CommentOutData>) =
        withContext(Dispatchers.IO) {
            database.insertComments(commentsListToEntityMapper(comment))
        }

    override suspend fun deleteCommentFromDb(comment: CommentOutData) =
        withContext(Dispatchers.IO) {
            database.deleteComment(commentsToEntityMapper(comment))
        }
}