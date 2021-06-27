package com.example.map.database

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import io.paperdb.Paper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PaperPrefs: CoroutineScope, LifecycleObserver {
    // Coroutine's background job
    private val job = Job()
    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job


    constructor(application: Application){
        Paper.init(application)
    }

    constructor(context: Context){
        Paper.init(context)
    }

    companion object{
        val PHONENUMBER_KEY = "iusniuffwewfwnnwei"
        val ISLOGGED = "iusnpofiuiuniernriniuffwewfwnnwei"
        val UUID = "wjmlmwfmmwfmmwe"
        val EMAIL = "JBDJN"
        val FIRSTNAME = "JBNJNQ"
        val LASTNAME ="JNOJA"
        val PUSHIDKEY = "WFIJWFMNNFEWWFWFN"
        val ISFIRSTTIMELAUNCH = "sfokmweom"
        val SHOW_INTRO = "SHOW_INTRO"
        val PHONESTATUS = "phonestatus"
        val REFERAL_LINK = "referal_link"
    }



    private fun getStringFromPref(key:String):String{
        return runBlocking {
            async(Dispatchers.IO){
                Paper.book().read(key,"")
            }.await()
        }
    }

    private fun getStringFromPref(key:String,deafult:String):String{
        return runBlocking {
            async(Dispatchers.IO){
                Paper.book().read(key,deafult)
            }.await()
        }
    }

    private fun saveBooleanToPref(key:String,value:Boolean){
        runBlocking {
            withContext(Dispatchers.IO){
                Paper.book().write(key,value)
            }
        }
    }

    private fun getBooleanFromPref(key:String):Boolean{
        return runBlocking {
            async(Dispatchers.IO){
                Paper.book().read(key,false)
            }.await()
        }
    }

    private fun getBooleanFromPref(key:String,defaultvalue:Boolean):Boolean{
        return runBlocking {
            async(Dispatchers.IO){
                Paper.book().read(key,defaultvalue)
            }.await()
        }
    }


    private fun <T:Any> saveAnyToPref(key: String,data:T){
        runBlocking {
            withContext(Dispatchers.IO){
                Paper.book().write(key,data)
            }
        }
    }




    private fun <T:Any> getAnyFromPref(key:String):T{
        return runBlocking {
            async(Dispatchers.IO){
                Paper.book().read(key) as T
            }.await()
        }
    }

    private fun saveStringToPref(key:String,value:String){
        runBlocking {
            withContext(Dispatchers.IO){
                Paper.book().write(key,value)
            }
        }
    }


    fun String.getStringPref():String{
        return getStringFromPref(this)
    }

    fun String.getStringPref(default:String):String{
        return getStringFromPref(this,default)
    }

    fun String.saveStringPref(value:String){

        saveStringToPref( this,value)
    }

    fun String.getBooleanPref():Boolean{
        return getBooleanFromPref(this)
    }
    fun String.getBooleanPref(default:Boolean):Boolean{
        return getBooleanFromPref(this,default)
    }

    fun <T:Any> String.getAnyPref():T{
        return getAnyFromPref(this)
    }
    fun <T:Any> String.saveAnyPref(data:T){
        return saveAnyToPref(this,data)
    }

    fun String.saveBooleanPref(value:Boolean){
        saveBooleanToPref(this,value)
    }




}