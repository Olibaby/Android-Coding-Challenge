package com.example.map.module

import com.amazonaws.auth.BasicAWSCredentials
import com.example.androidcodingchallenge.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ HomeViewModel(homeRepository = get()) }
}
