package com.example.map.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.thetechnocafe.gurleensethi.liteutils.RecyclerAdapterUtil


fun <T:Any> RecyclerView.updateRecycler(context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit,noImageViews:List<View>): RecyclerView.Adapter<*>? {
    if(listOfItems.isNullOrEmpty()){
        this.visibility = View.GONE
        noImageViews.forEach { it.visibility = View.VISIBLE }
    }else{
        this.visibility = View.VISIBLE
        noImageViews.forEach { it.visibility = View.GONE }
    }
    this.layoutManager = LinearLayoutManager(context)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }

    this.adapter = reyclerAdaptor


    return adapter
}

fun <T:Any> RecyclerView.updateRecycler(context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager = LinearLayoutManager(context)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }

    this.adapter = reyclerAdaptor


    return adapter
}

fun <T:Any> RecyclerView.updateRecycler2(context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int,T) -> Unit, onClickPosition:(Int,T) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager = LinearLayoutManager(context)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position,item)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position,item) }

    this.adapter = reyclerAdaptor


    return adapter
}
fun <T:Any> RecyclerView.updateRecyclerLayout(context: Context, layoutManager: RecyclerView.LayoutManager, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager = layoutManager
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }

    this.adapter = reyclerAdaptor


    return adapter
}
fun <T:Any> RecyclerView.updateRecyclerLayoutBinder(context: Context, layoutManager: RecyclerView.LayoutManager, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager = layoutManager
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }

    this.adapter = reyclerAdaptor


    return adapter
}
fun <T:Any> RecyclerView.updateRecyclerStaggered(context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager = StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }

    this.adapter = reyclerAdaptor


    return adapter
}





