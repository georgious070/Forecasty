package com.android.forecasty.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.forecasty.R
import com.android.forecasty.data.model.cycle.ListItem
import kotlinx.android.synthetic.main.recycler_item.view.*

class CityViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)) {

    fun bindTo(cityDescription: ListItem) {
        itemView.text_city_name_int_recycler.text = cityDescription.name
    }
}