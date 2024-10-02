package com.example.u20221745.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.u20221745.R
import com.example.u20221745.adapters.FavoriteNewsRecyclerViewAdapter
import com.example.u20221745.db.AppDatabase
import kotlinx.coroutines.launch

class FavoriteNewsActivity : AppCompatActivity() {

    private lateinit var adapter : FavoriteNewsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorite_news)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.tbFavoriteNews)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.rvFavoriteNews)
        adapter = FavoriteNewsRecyclerViewAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        observeFavoriteNews()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Handle the back button press
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeFavoriteNews() {
        val database = AppDatabase.getInstance(this)
        val favoriteNewsDao = database.newsDao()
        lifecycleScope.launch {
            favoriteNewsDao.getAllNews().collect { favoriteNews ->
                adapter.updateNews(favoriteNews)
            }
        }
    }
}