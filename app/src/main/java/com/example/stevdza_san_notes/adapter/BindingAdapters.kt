package com.example.stevdza_san_notes.adapter

import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.stevdza_san_notes.R
import com.example.stevdza_san_notes.room.data.Priority

class BindingAdapters {

    companion object{

        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView, priority: Priority){
            when(priority){
                Priority.HIGH -> { cardView.setCardBackgroundColor(cardView.context.getColor(R.color.red)) }
                Priority.MEDIUM -> { cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow)) }
                Priority.LOW -> { cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green)) }
            }
        }

    }
}