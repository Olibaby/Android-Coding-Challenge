package com.example.androidcodingchallenge

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.androidcodingchallenge.model.response.Card

class TextViewHolder(val view: View): PageFeedViewHolder<Card>(view) {
    private val itemContent = view.findViewById<TextView>(R.id.tvTitle)

    override fun bind(item: Card) {
        itemContent.text = item.card.value
        itemContent.textSize = item.card.attributes.font.size.toFloat()
        itemContent.setTextColor(Color.parseColor(item.card.attributes.text_color))

    }

}
