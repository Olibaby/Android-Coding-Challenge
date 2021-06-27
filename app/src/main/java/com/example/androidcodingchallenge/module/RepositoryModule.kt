package com.example.map.module

import com.example.androidcodingchallenge.repository.HomeRepository
import com.example.androidcodingchallenge.repository.HomeRepositoryImpl
import com.qucoon.rubiescircle.utils.SingleLiveEvent
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repoModule = module {
    single<HomeRepository> { HomeRepositoryImpl(homepagefeedsapi = get(), cardDao = get()) }
}