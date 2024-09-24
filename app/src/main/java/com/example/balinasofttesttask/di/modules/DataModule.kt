package com.example.balinasofttesttask.di.modules


import com.example.balinasofttesttask.data.repositoriesimpl.CommentsRepositoryImpl
import com.example.balinasofttesttask.data.repositoriesimpl.ImageRepositoryImpl
import com.example.balinasofttesttask.data.repositoriesimpl.UserRepositoryImpl
import com.example.balinasofttesttask.domain.repositories.CommentRepository
import com.example.balinasofttesttask.domain.repositories.ImageRepository
import com.example.balinasofttesttask.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun getUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun getImageRepository(impl: ImageRepositoryImpl): ImageRepository

    @Binds
    @Singleton
    fun getCommentRepository(impl: CommentsRepositoryImpl): CommentRepository
}
