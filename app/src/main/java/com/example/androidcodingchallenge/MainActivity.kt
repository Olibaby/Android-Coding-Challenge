package com.example.androidcodingchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidcodingchallenge.base.BaseActivity
import com.example.androidcodingchallenge.fragment.HomePageFragment

class MainActivity : BaseActivity() {

    private val baseFragment = lazy {
        listOf(HomePageFragment())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        initFragNavController(this, baseFragment.value, TAG, supportFragmentManager, R.id.content)
    }

    companion object{
        val TAG = this.javaClass.canonicalName ?: "Main"
    }

}