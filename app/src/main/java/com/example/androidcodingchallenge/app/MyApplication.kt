package com.example.androidcodingchallenge.app

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.example.androidcodingchallenge.module.dbModules
import com.example.map.module.networkModule
import com.example.map.module.repoModule
import com.example.map.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class MyApplication :MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(networkModule, repoModule, viewModelModule, dbModules) )
        }
    }

    private fun initTimber() {
        if (true) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree)
        }
    }

}
object CrashReportingTree: Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }
        CrashReportingTree.log(priority, tag, message)
        if (t != null) {
            if (priority == Log.ERROR) {
               // CrashLibrary.logError(t);
            } else if (priority == Log.WARN) {
                //CrashLibrary.logWarning(t);
            }
        }
    }

}