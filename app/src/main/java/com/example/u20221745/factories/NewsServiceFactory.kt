package com.example.u20221745.factories

import com.example.u20221745.constants.Constant
import com.example.u20221745.network.NewsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsServiceFactory {

    fun createNewsServiceInstance(): NewsService {
        return Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }
}