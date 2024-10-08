package com.example.balinasofttesttask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ImageEntity::class, CommentEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getImageDao(): ImageDao

    abstract fun getCommentDao(): CommentDao
}