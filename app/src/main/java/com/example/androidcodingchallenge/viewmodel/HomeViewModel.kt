package com.example.androidcodingchallenge.viewmodel

import androidx.lifecycle.asLiveData
import com.example.androidcodingchallenge.model.response.HomePageResponse
import com.example.androidcodingchallenge.repository.HomeRepository
import com.example.map.base.BaseViewModel
import com.qucoon.rubiescircle.utils.SingleLiveEvent

class HomeViewModel(private val homeRepository: HomeRepository): BaseViewModel() {
    val getHomePageFeedsResponse = SingleLiveEvent<HomePageResponse>()

    fun getHomePageFeeds(){
        apiGetRequest(homeRepository::getHomePageFeeds, getHomePageFeedsResponse, {"Try Again"}, false)
    }

    fun getHomePageFeedsFromDb() = homeRepository.getAllCards().asLiveData()


}