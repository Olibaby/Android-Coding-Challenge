package com.example.map.module

import com.example.androidcodingchallenge.BuildConfig
import com.example.androidcodingchallenge.network.HOMEPAGEFEEDSAPI
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

const val HOMEPAGEFEEDSAPIBASEURL = "https://private-8ce77c-tmobiletest.apiary-mock.com/"

val networkModule = module {
    single { createWebService<HOMEPAGEFEEDSAPI>(RxJava2CallAdapterFactory.create(), HOMEPAGEFEEDSAPIBASEURL) }


}
/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    factory: CallAdapter.Factory, baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient())

        .build()
    return retrofit.create(T::class.java)
}

//inline fun <reified  T> createWebSocket(okHttpClient: OkHttpClient, application: Application, paperPref: PaperPref):T{
//    val scarlet = Scarlet.Builder()
//        .webSocketFactory(okHttpClient.newWebSocketFactory(getSocketBASEURL(Utils.formatPhone(paperPref.getStringPref(PaperPref.PHONE_NUMBER)) )))
//        .addMessageAdapterFactory(GsonMessageAdapter.Factory())
//        .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
//        .lifecycle(createAppForegroundAndUserLoggedInLifecycle(application))
//        .build()
//    return scarlet.create(T::class.java)
//}

//fun getSocketBASEURL(phoneNumber: String):String{
//    return "ws://35.167.220.206/veezahsocket/initialize?username=$phoneNumber&source=Mobile"
//}
//
//fun createAppForegroundAndUserLoggedInLifecycle(application: Application): Lifecycle {
//    return AndroidLifecycle.ofApplicationForeground(application)
//}
fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

}




fun okHttpClient() =   OkHttpClient.Builder()
    .addInterceptor(headersInterceptor())
    .addInterceptor(loggingInterceptor())
    .readTimeout(5, TimeUnit.MINUTES)
    .connectTimeout(  5, TimeUnit.MINUTES)
    .writeTimeout(5, TimeUnit.MINUTES)
    .build()

fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(chain.request().newBuilder()
        .addHeader("Content-Type", "application/json")
        // .addHeader("Authorization", Paper.book().read("uwinnf34",""))
        .build())
}