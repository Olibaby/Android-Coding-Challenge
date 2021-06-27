package com.example.map.utils

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.example.map.database.PaperPrefs
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


fun View.hide(){
    this.visibility = View.GONE
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.makeInviible(){
    this.visibility = View.INVISIBLE
}

fun PaperPrefs.savePref(key: String, value: Any){

    when(value){
        is String -> {key.saveStringPref(value)}
        is Boolean -> {key.saveBooleanPref(value)}
        else -> {
            key.saveAnyPref(value)
        }
    }
}

fun Long.asDateString(format: String? = "yyyy-MM-dd HH:mm:ss"): String = Date(this).asDateString(format)
fun Date.asDateString(format: String? = "yyyy-MM-dd HH:mm:ss"): String = SimpleDateFormat(format, Locale.getDefault()).format(this)


fun tryAction(action:()->Unit){
    return try {
        action()
    }catch (ex:Exception){
        Timber.e(ex)
    }
}


fun <T:Any> tryOperation(action:()->UseCaseResult<T>):UseCaseResult<T>{
    return try {
        action()
    }catch (ex:Exception){
        Timber.e(ex)
        UseCaseResult.Error(ex)
    }
}
fun  tryBackgroundOperation(action:()->Boolean):Boolean{
    return try {
        action()
    }catch (ex:Exception){
        Timber.e(ex)
        false
    }
}

fun  PaperPrefs.getBooleanPref(key: String):Boolean{
    return key.getBooleanPref()
}
fun  PaperPrefs.getBooleanPref(key: String,defaultValue:Boolean):Boolean{
    return key.getBooleanPref(defaultValue)
}

fun <T:Any> PaperPrefs.getAnyPref(key: String):T{
    return key.getAnyPref()
}
fun PaperPrefs.getStringPref(key: String):String{
    return key.getStringPref()
}

fun ViewPager.setUpViewPager(viewPagerObjectList:List<ViewPagerObject>,fragmentStateManager: FragmentManager){
    val pagerAdapter = Utils.MyViewPageStateAdapter(fragmentStateManager)
    viewPagerObjectList.forEach {
        pagerAdapter.addFragment(it.fragment,it.title)
    }
    this.apply {
        adapter = pagerAdapter
    }
}
fun ViewPager.setUpViewPager(viewPagerObjectList:List<ViewPagerObject>,transform: ViewPager.PageTransformer,onPageChangeListener: ViewPager.OnPageChangeListener,fragmentStateManager: FragmentManager){
    val pagerAdapter = Utils.MyViewPageStateAdapter(fragmentStateManager)
    viewPagerObjectList.forEach {
        pagerAdapter.addFragment(it.fragment,it.title)
    }
    this.apply {
         adapter = pagerAdapter
        setPageTransformer(true,transform)
        addOnPageChangeListener(onPageChangeListener)
    }
}
data class ViewPagerObject(val fragment: Fragment, val title:String)

//fun ViewPager.setUpSlidingViewPager(fm: FragmentManager,viewpagerItems:List<ViewPagerObject>,page_margin:Int = 48,padding_left:Int =88,padding_top:Int =8,
//                                    padding_right:Int =88,padding_bottom :Int = 8
//                                    ){
//
//    this.apply{
//        adapter = ViewPagerAdaptor(fm,viewpagerItems)
//        clipToPadding = false
//        pageMargin = page_margin
//        setPadding(padding_left,padding_top,padding_right,padding_bottom)
//        offscreenPageLimit = 3
//    }
//    val adaptor = this.adapter
//    var viewpager = this
//    this.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
//        override fun onPageScrollStateChanged(state: Int) {
//            if (state == ViewPager.SCROLL_STATE_IDLE) {
//                var fragment = (adapter as ViewPagerAdaptor).getRegisteredFragment(viewpager.currentItem)
//                when(fragment){
//                    is CardInterface ->   fragment.scaleImage(1F)
//                }
//
//                if (viewpager.currentItem > 0) {
//                    fragment = (adapter as ViewPagerAdaptor).getRegisteredFragment(viewpager.currentItem - 1)
//                    when(fragment){
//                        is CardInterface ->   fragment.scaleImage((1 - RATIO_SCALE).toFloat())
//                    }
//
//                }
//
//                if (viewpager.currentItem + 1 < (viewpager.adapter as ViewPagerAdaptor).count) {
//                    fragment = (adapter as ViewPagerAdaptor).getRegisteredFragment(viewpager.currentItem + 1)
//                    when(fragment){
//                        is CardInterface ->   fragment.scaleImage((1 - RATIO_SCALE).toFloat())
//                    }
//
//                }
//            }
//        }
//
//        override fun onPageScrolled(
//            position: Int,
//            positionOffset: Float,
//            positionOffsetPixels: Int
//        ) {
//            var sampleFragment = ( adaptor as ViewPagerAdaptor).getRegisteredFragment(position)
//            var scale = 1 - positionOffset * RATIO_SCALE
//            // Just a shortcut to findViewById(R.id.image).setScale(scale);
//            when(sampleFragment){
//                is CardInterface ->   sampleFragment.scaleImage(scale.toFloat())
//            }
//            if (position + 1 < (adapter as ViewPagerAdaptor).count) {
//                sampleFragment = (adapter as ViewPagerAdaptor).getRegisteredFragment(position + 1)
//                scale = positionOffset * RATIO_SCALE + (1 - RATIO_SCALE)
//                when(sampleFragment){
//                    is CardInterface ->   sampleFragment.scaleImage(scale.toFloat())
//                }
//            }
//        }
//
//        override fun onPageSelected(position: Int) {
//
//        }
//
//    })
//}
class ViewPagerAdaptor(fm: FragmentManager, viewpagerItems:List<ViewPagerObject>) : FragmentStatePagerAdapter(fm) {
    private var registeredFragments = SparseArray<Fragment>()
    private val viewpagerItems = viewpagerItems

    override fun getCount(): Int {
        return viewpagerItems.size
    }

    override fun getItem(position: Int): Fragment {
        return viewpagerItems[position].fragment
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    fun getRegisteredFragment(position: Int): Fragment {
        return registeredFragments.get(position)
    }
}

fun ImageView.loadImage(resorceID:Int, fragment: Fragment){
    Glide.with(fragment)
        .load(resorceID)
        .into(this);
}

fun String?.getString():String{
    return this ?: ""
}

fun ImageView.loadImage(fullImageUrl: String, defaultImage:Int, view: Fragment) {
    Glide.with(view).load(fullImageUrl)
        .placeholder(defaultImage)
        .error(defaultImage)
        .into(this)
}
fun ImageView.loadImage(fullImageUrl: String, view: Context, width: Int, height: Int) {
    val requestOption = RequestOptions()
        .signature(ObjectKey(System.currentTimeMillis().toString()))
    Glide.with(view).load(fullImageUrl)
        .override(width, height)
        .dontAnimate()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(requestOption)
        .into(this)
}
fun Date.toDateString(targetFormant: String): String {
    val sdf = SimpleDateFormat(targetFormant)
    val calendar = Calendar.getInstance()
    calendar.time = this
    return sdf.format(calendar.time)
}