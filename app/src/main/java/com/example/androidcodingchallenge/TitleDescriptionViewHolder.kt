package com.example.androidcodingchallenge

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.androidcodingchallenge.model.response.Card

class TitleDescriptionViewHolder(val view: View): PageFeedViewHolder<Card>(view) {
    private val itemTitle = view.findViewById<TextView>(R.id.tvTitle1)
    private val itemDescription = view.findViewById<TextView>(R.id.tvDescription1)

    override fun bind(item: Card) {
        itemTitle.text = item.card.title.value
        itemTitle.textSize = item.card.title.attributes.font.size.toFloat()
        itemTitle.setTextColor(Color.parseColor(item.card.title.attributes.text_color))

        itemDescription.text = item.card.description.value
        itemDescription.textSize = item.card.description.attributes.font.size.toFloat()
        itemDescription.setTextColor(Color.parseColor(item.card.description.attributes.text_color))
    }
}

