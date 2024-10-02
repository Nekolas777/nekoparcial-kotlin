package com.example.u20221745.network

import com.example.u20221745.communication.NewsApiResponse
import com.example.u20221745.constants.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(Constant.RESOURCE_PATH)
    suspend fun getNewsByName(
        @Query("description") description: String
    ): List<NewsApiResponse>
}