package com.vocera.recyclerviewbug

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class MainViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    val textView = view.findViewById<TextView>(R.id.text_item)
}