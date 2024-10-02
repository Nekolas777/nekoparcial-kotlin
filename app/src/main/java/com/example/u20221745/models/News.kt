package com.example.u20221745.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Source(
    val name: String?
)

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val source: String?,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)