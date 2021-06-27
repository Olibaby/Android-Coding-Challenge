package com.example.androidcodingchallenge.network

import com.example.androidcodingchallenge.model.response.HomePageResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HOMEPAGEFEEDSAPI {
    @GET("test/home")
    fun getHomePageFeeds() : Deferred<HomePageResponse>
}





