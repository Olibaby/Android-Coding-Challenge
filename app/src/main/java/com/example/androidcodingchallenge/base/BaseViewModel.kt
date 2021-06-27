package com.example.map.base

import androidx.lifecycle.*
import com.example.map.utils.UseCaseResult
import com.example.map.utils.handleException
import com.qucoon.rubiescircle.utils.SingleLiveEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel:ViewModel(), CoroutineScope,LifecycleObserver {
    // Coroutine's background job
    private val job = Job()
    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String>()
    val showSessionTimeOut= SingleLiveEvent<String>()
    override fun onCleared() {
        super.onCleared()
        // Clear our job when the linked activity is destroyed to avoid memory leaks
        job.cancel()
    }

    fun <T:Any> apiGetRequest(apiCall:suspend ()-> UseCaseResult<T>, observer:SingleLiveEvent<T>, getError:(response:T) -> String, enableLoading:Boolean = true){
        if(enableLoading) showLoading.value = true
        launch {
            val response = withContext(Dispatchers.IO){apiCall()}
            if (enableLoading) showLoading.value = false
            when(response){
                is UseCaseResult.Success -> observer.value = response.data
                is UseCaseResult.FailedAPI -> showError.value = getError(response.data)
                is UseCaseResult.Error -> showError.value = response.exception.handleException()
            }
        }
    }

    fun <R:Any,T:Any> apiRequest(request:R, apiCall:suspend (request:R)-> UseCaseResult<T>, observer:SingleLiveEvent<T>, getError:(response:T) -> String,enableLoading:Boolean = true){
        if(enableLoading) showLoading.value = true
        launch {
            val response = withContext(Dispatchers.IO){apiCall(request)}
            if (enableLoading) showLoading.value = false
            when(response){
                is UseCaseResult.Success -> observer.value = response.data
                is UseCaseResult.FailedAPI -> showError.value = getError(response.data)
                is UseCaseResult.Error -> showError.value = response.exception.handleException()
            }
        }
    }
    fun <R:Any,T:Any> apiRequest(request:R, apiCall:suspend (request:R)-> UseCaseResult<T>, observer:SingleLiveEvent<T>, getError:(response:T) -> String,
                                 onSuccessOperations:(response:T) -> Unit,enableLoading:Boolean = true
    ){
        if(enableLoading) showLoading.value = true
        launch {
            val response = withContext(Dispatchers.IO){apiCall(request)}
            if (enableLoading) showLoading.value = false
            when(response){
                is UseCaseResult.Success -> {
                    onSuccessOperations(response.data)
                    observer.value = response.data
                }
                is UseCaseResult.FailedAPI -> showError.value = getError(response.data)
                is UseCaseResult.Error -> showError.value = response.exception.handleException()
            }
        }
    }
}


fun <T> SingleLiveEvent<T>.observeUnit(owner: LifecycleOwner,action:(T?)->Unit){
    this.observe(owner, Observer { action(it) })
}

fun <T> SingleLiveEvent<T>.observeChange(owner: LifecycleOwner,action:(T)->Unit){
    this.observe(owner, Observer { action(it) })
}
fun <T> LiveData<T>.observeChange(owner: LifecycleOwner,action:(T)->Unit){
    this.observe(owner, Observer { action(it) })
}
fun <T> MutableLiveData<T>.observeChange(owner: LifecycleOwner,action:(T)->Unit){
    this.observe(owner, Observer { action(it) })
}