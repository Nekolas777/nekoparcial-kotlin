package com.example.u20221745.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.u20221745.models.News
import kotlinx.coroutines.flow.Flow
@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): Flow<List<News>>

    // by author
    @Query("SELECT * FROM news WHERE author = :author")
    fun getNewByAuthor(author: String): News

    // by title
    @Query("SELECT * FROM news WHERE title = :title")
    fun getNewByTitle(title: String): News

    @Query("SELECT * FROM news WHERE id = :id")
    fun getNewById(id: Int): News

    @Insert
    fun insertNew(news: News)

    @Delete
    fun deleteNew(news: News)
}