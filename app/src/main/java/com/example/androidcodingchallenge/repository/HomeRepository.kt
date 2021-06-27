package com.example.androidcodingchallenge.repository

import com.example.androidcodingchallenge.database.dao.CardDao
import com.example.androidcodingchallenge.model.response.Card
import com.example.androidcodingchallenge.model.response.HomePageResponse
import com.example.androidcodingchallenge.network.HOMEPAGEFEEDSAPI
import com.example.map.base.BaseRepository
import com.example.map.utils.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface HomeRepository {
    suspend fun getHomePageFeeds(): UseCaseResult<HomePageResponse>
    fun getAllCards(): Flow<List<Card>>
}

class HomeRepositoryImpl(private val homepagefeedsapi: HOMEPAGEFEEDSAPI, private val cardDao: CardDao): BaseRepository(), HomeRepository{

    override suspend fun getHomePageFeeds(): UseCaseResult<HomePageResponse> {
        return safeGetApiCall(homepagefeedsapi::getHomePageFeeds,{true}, this::updateCards)
    }

    override fun getAllCards(): Flow<List<Card>> {
        return cardDao.getAllCards()
    }

    suspend fun updateCards(response: HomePageResponse){
        response.let {
            withContext(Dispatchers.IO){
                cardDao.updateCards(it.page.cards)
            }
        }
    }
}