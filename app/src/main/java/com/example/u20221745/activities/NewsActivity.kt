package com.example.u20221745.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.u20221745.R
import com.example.u20221745.adapters.NewsRecyclerViewAdapter
import com.example.u20221745.factories.NewsServiceFactory
import kotlinx.coroutines.launch

class NewsActivity : AppCompatActivity() {

    var newsService = NewsServiceFactory().createNewsServiceInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edNewsName = findViewById<EditText>(R.id.edNewsName)
        val btnSearchNews = findViewById<Button>(R.id.btnSearchNews)

        btnSearchNews.setOnClickListener {
            lifecycleScope.launch {
                getNews(edNewsName.text.toString())
            }
        }
    }

    private suspend fun getNews(newsName: String){
        val news = newsService.getNewsByName(newsName)
        val rvNews = findViewById<RecyclerView>(R.id.rvNews)
        rvNews.adapter = NewsRecyclerViewAdapter(news)
        rvNews.layoutManager = LinearLayoutManager(this)
    }
}