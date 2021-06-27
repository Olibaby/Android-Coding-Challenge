package com.example.androidcodingchallenge.module

import androidx.room.Room
import com.example.androidcodingchallenge.database.roomdatabase.CodingChallengeRoomDatabase
import com.example.map.database.PaperPrefs
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModules = module {
    // Room Database instance
    // Room Database instance
    single {  Room.databaseBuilder(androidApplication(), CodingChallengeRoomDatabase::class.java, "codingchallenge-db").
        fallbackToDestructiveMigration()
        .build() }

    single { PaperPrefs(application = androidApplication()) }
    //Dao
    val createAtStart = false
    single(createdAtStart = createAtStart) { get<CodingChallengeRoomDatabase>().cardDao() }




}
