package com.example.map.utils

import android.content.Context
import android.os.Environment
import android.os.Handler
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bumptech.glide.load.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Utils {


    fun getDeviceID(context: Context):String{
      return  Settings.Secure.getString(context.contentResolver,
            Settings.Secure.ANDROID_ID)
    }
    class MyViewPageStateAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
        val fragmentList:MutableList<Fragment> = ArrayList<Fragment>()
        val fragmentTitleList:MutableList<String> = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList.get(position)
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList.get(position)
        }

        fun addFragment(fragment: Fragment, title:String){
            fragmentList.add(fragment)
            fragmentTitleList.add(title)

        }


    }
    fun delayFor(millseconds:Long,action:() -> Unit ){
        Handler().postDelayed({
            action()
        }, millseconds)
    }
    fun writeToPath(pathName:String):String{
        return Environment.getExternalStorageDirectory().absolutePath + pathName
    }

    fun handleException(exception: Throwable):String {
        println(exception)
        return  when (exception) {
            is SocketTimeoutException -> "Request time out. Try again"
            is UnknownHostException -> "Check your internet connection"
            is HttpException -> "Check your internet connection"
            is ConnectException -> "Check your internet connection"
            else -> "Something went wrong"

        }
    }
}