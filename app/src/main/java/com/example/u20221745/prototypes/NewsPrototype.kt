package com.example.u20221745.prototypes

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.u20221745.R
import com.example.u20221745.communication.NewsApiResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsPrototype(itemView: View): RecyclerView.ViewHolder(itemView) {

    val tvAuthor = itemView.findViewById<TextView>(R.id.tvAuthor)
    val tvTitle = itemView.findViewById<TextView>(R.id.tvNewsTitle)
    val publishedAt = itemView.findViewById<TextView>(R.id.tvPublishedDate)
    val ivNews = itemView.findViewById<ImageView>(R.id.ivNewsImage)

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    fun bind (newsItem: NewsApiResponse) {
        tvAuthor.text = "Author: ${newsItem.author}"
        tvTitle.text = "Title: ${newsItem.title}"
        // Parse the publishedAt date and extract the year
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val dateTime = LocalDateTime.parse(newsItem.publishedAt, formatter)
        val year = dateTime.year

        publishedAt.text = "Published at: $year"
        val imageUrl = newsItem.urlToImage
        Glide.with(itemView.context)
            .load(imageUrl)
            .error(R.drawable.main_banner)
            .into(ivNews)
    }

}