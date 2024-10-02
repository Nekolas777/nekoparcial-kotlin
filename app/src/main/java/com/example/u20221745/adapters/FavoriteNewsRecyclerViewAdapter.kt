package com.example.u20221745.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.u20221745.R
import com.example.u20221745.db.AppDatabase
import com.example.u20221745.models.News
import com.example.u20221745.prototypes.FavoriteNewsPrototype

class FavoriteNewsRecyclerViewAdapter(private var favoriteNewsList: List<News>): RecyclerView.Adapter<FavoriteNewsPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteNewsPrototype {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.favorite_news_card, parent, false)
        return FavoriteNewsPrototype(layout)
    }

    override fun getItemCount(): Int {
        return favoriteNewsList.size
    }

    override fun onBindViewHolder(holder: FavoriteNewsPrototype, position: Int) {
        holder.bind(favoriteNewsList[position])
        val btnDelete = holder.itemView.findViewById<ImageButton>(R.id.ibDeleteNews)

        btnDelete.setOnClickListener {
            val database = AppDatabase.getInstance(holder.itemView.context)
            val favoriteNewsDao = database.newsDao()
            favoriteNewsDao.deleteNew(favoriteNewsList[position])
            Toast.makeText(
                holder.itemView.context,
                "News number ${favoriteNewsList[position].id} removed from favorites",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(newNews: List<News>) {
        favoriteNewsList = newNews
        notifyDataSetChanged()
    }


}