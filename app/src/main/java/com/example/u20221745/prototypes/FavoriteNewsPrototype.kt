package com.example.u20221745.prototypes

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.u20221745.R
import com.example.u20221745.models.News

class FavoriteNewsPrototype(itemView: View): RecyclerView.ViewHolder(itemView) {

    val tvAuthor = itemView.findViewById<TextView>(R.id.tvAuthorFavorite)
    val tvTitle = itemView.findViewById<TextView>(R.id.tvTitleFavorite)
    val tvDescription = itemView.findViewById<TextView>(R.id.tvDescriptionFavorite)
    val ivNewsFavorite = itemView.findViewById<ImageView>(R.id.ivNewsFavorite)

    fun bind (favoriteNewsItem: News) {
        tvAuthor.text = "Author: ${favoriteNewsItem.author}"
        tvTitle.text = "Title: ${favoriteNewsItem.title}"
        tvDescription.text = "Description: ${favoriteNewsItem.description}"
        val imageUrl = favoriteNewsItem.urlToImage
        Glide.with(itemView.context)
            .load(imageUrl)
            .error(R.drawable.main_banner)
            .into(ivNewsFavorite)
    }

}