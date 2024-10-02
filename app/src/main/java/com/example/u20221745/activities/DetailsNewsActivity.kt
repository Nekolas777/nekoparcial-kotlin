package com.example.u20221745.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.u20221745.R
import com.example.u20221745.db.AppDatabase
import com.example.u20221745.models.News

class DetailsNewsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details_news)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.tbDetailsNews)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val source = intent.getStringExtra("news_source")
        val title = intent.getStringExtra("news_title")
        val author = intent.getStringExtra("news_author")
        val description = intent.getStringExtra("news_description")
        val urlToImage = intent.getStringExtra("news_urlToImage")
        val publishedAt = intent.getStringExtra("news_publishedAt")
        val content = intent.getStringExtra("news_content")
        val url = intent.getStringExtra("news_url")

        val btnReadMore = findViewById<TextView>(R.id.btnWebsite)
        val btnSaveFavorite = findViewById<TextView>(R.id.btnRegisterFavoriteDetail)

        findViewById<TextView>(R.id.tvSourceNewsDetail).text = source
        findViewById<TextView>(R.id.tvNewsContentDetail).text = content

        val imageView = findViewById<ImageView>(R.id.ivNewsDetails)
        Glide.with(this)
            .load(urlToImage)
            .error(R.drawable.main_banner)
            .into(imageView)

        btnReadMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        btnSaveFavorite.setOnClickListener {
            val database = AppDatabase.getInstance(this)
            val favoriteNewsDao = database.newsDao()
            val favoriteNew = News(
                id = null,
                source = author ?: "Unknown Source",
                author = author ?: "Unknown Author",
                title = title ?: "No Title",
                description = description ?: "No Description",
                url = url ?: "No URL",
                urlToImage = urlToImage ?: "No Image URL",
                publishedAt = publishedAt ?: "No Date",
                content = content ?: "No Content"
            )

            val favoriteNewsExists = favoriteNewsDao.getNewByTitle(title ?: "No Title")

            if (favoriteNewsExists != null) {
                Toast.makeText(this, "News $title already in favorites", Toast.LENGTH_SHORT).show()
            } else {
                favoriteNewsDao.insertNew(favoriteNew)
                Toast.makeText(this, "News $title added to favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}