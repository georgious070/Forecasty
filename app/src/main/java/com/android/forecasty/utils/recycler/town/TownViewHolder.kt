package com.android.forecasty.utils.recycler.town

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.forecasty.R
import com.android.forecasty.data.repository.town.DayData
import kotlinx.android.synthetic.main.recycler_date_item.view.*

class TownViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycler_date_item, parent, false)) {

    fun bindTo(item: DayData) {
        itemView.city_date.text = item.day
    }
}