package com.example.u20221745.adapters

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.u20221745.R
import com.example.u20221745.activities.DetailsNewsActivity
import com.example.u20221745.communication.NewsApiResponse
import com.example.u20221745.db.AppDatabase
import com.example.u20221745.models.News
import com.example.u20221745.prototypes.NewsPrototype

class NewsRecyclerViewAdapter(private val news: List<NewsApiResponse>) :
    RecyclerView.Adapter<NewsPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return NewsPrototype(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsPrototype, position: Int) {
        holder.bind(news[position])
        val btnFavorite = holder.itemView.findViewById<ImageButton>(R.id.ibFavoriteNews)
        val btnDetails = holder.itemView.findViewById<ImageButton>(R.id.ibDetail)

        btnDetails.setOnClickListener {
            val newsItem = news[position]
            val context = holder.itemView.context
            val intent = Intent(context, DetailsNewsActivity::class.java).apply {
                putExtra("news_source", newsItem.source?.name)
                putExtra("news_title", newsItem.title)
                putExtra("news_author", newsItem.author)
                putExtra("news_description", newsItem.description)
                putExtra("news_url", newsItem.url)
                putExtra("news_urlToImage", newsItem.urlToImage)
                putExtra("news_publishedAt", newsItem.publishedAt)
                putExtra("news_content", newsItem.content)
            }
            context.startActivity(intent)
        }

        btnFavorite.setOnClickListener {
            val database = AppDatabase.getInstance(holder.itemView.context)
            val favoriteNewsDao = database.newsDao()
            val newsItem = news[position]
            val favoriteNew = News(
                id = null,
                source = newsItem.source?.name ?: "Unknown Source",
                author = newsItem.author ?: "Unknown Author",
                title = newsItem.title ?: "No Title",
                description = newsItem.description ?: "No Description",
                url = newsItem.url ?: "No URL",
                urlToImage = newsItem.urlToImage ?: "No Image URL",
                publishedAt = newsItem.publishedAt ?: "No Date",
                content = newsItem.content ?: "No Content"
            )

            val favoriteNewsExists = favoriteNewsDao.getNewByTitle(newsItem.title ?: "No Title")

            if (favoriteNewsExists != null) {
                Toast.makeText(holder.itemView.context, "News ${newsItem.title} already in favorites", Toast.LENGTH_SHORT).show()
            } else {
                favoriteNewsDao.insertNew(favoriteNew)
                Toast.makeText(holder.itemView.context, "News ${newsItem.title} added to favorites", Toast.LENGTH_SHORT).show()
            }
        }

    }
}