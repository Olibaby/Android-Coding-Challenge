package com.example.androidcodingchallenge

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.androidcodingchallenge.model.response.Card
import com.example.map.utils.loadImage

class ImageTitleDescriptionViewHolder(val view: View): PageFeedViewHolder<Card>(view) {
    private val itemImage = view.findViewById<ImageView>(R.id.ivImage)
    private val itemTitle = view.findViewById<TextView>(R.id.tvTitle2)
    private val itemDescription = view.findViewById<TextView>(R.id.tvDescription2)

    override fun bind(item: Card) {
        itemImage.loadImage("https://qaevolution.blob.core.windows.net/assets/ios/3x/Featured@4.76x.png", view.context, item.card.image.size.width, item.card.image.size.height)

        itemTitle.text = item.card.title.value
        itemTitle.textSize = item.card.title.attributes.font.size.toFloat()
        itemTitle.setTextColor(Color.parseColor(item.card.title.attributes.text_color))

        itemDescription.text = item.card.description.value
        itemDescription.textSize = item.card.description.attributes.font.size.toFloat()
        itemDescription.setTextColor(Color.parseColor(item.card.description.attributes.text_color))

    }
}


