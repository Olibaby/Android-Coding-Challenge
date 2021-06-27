package com.example.androidcodingchallenge

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcodingchallenge.model.response.Card

class HomePageFeedAdapter(var pageFeed: MutableList<Card>): RecyclerView.Adapter<PageFeedViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageFeedViewHolder<*> {
        val context = parent.context
        return when (viewType) {
            CardType.TEXT.viewType -> {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.item_text, parent, false)
                TextViewHolder(view)
            }
            CardType.TITLEDESCRIPTION.viewType -> {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.item_title_description, parent, false)
                TitleDescriptionViewHolder(view)
            }
            CardType.IMAGETITLEDESCRIPTION.viewType -> {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.item_image_title_description, parent, false)
                ImageTitleDescriptionViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return pageFeed.size
    }

    fun updateIems(items:List<Card>){
        pageFeed.clear()
        pageFeed.addAll(items)
    }

    override fun onBindViewHolder(holder: PageFeedViewHolder<*>, position: Int) {
        val item = pageFeed[position]
        Log.d("adapter View", position.toString() + item.card_type)
        when (holder) {
            is TextViewHolder -> holder.bind(item)
            is TitleDescriptionViewHolder -> holder.bind(item)
            is ImageTitleDescriptionViewHolder -> holder.bind(item)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int = getViewType(pageFeed[position].card_type)

    private fun getViewType(cardType: String): Int {
      return  when(cardType){
            CardType.TEXT.key -> CardType.TEXT.viewType
            CardType.TITLEDESCRIPTION.key -> CardType.TITLEDESCRIPTION.viewType
            CardType.IMAGETITLEDESCRIPTION.key -> CardType.IMAGETITLEDESCRIPTION.viewType
          else -> CardType.TEXT.viewType
        }
    }

}

enum class CardType(val key:String,val viewType:Int){
    TEXT("text",0),TITLEDESCRIPTION("title_description",1), IMAGETITLEDESCRIPTION("image_title_description",2)
}

