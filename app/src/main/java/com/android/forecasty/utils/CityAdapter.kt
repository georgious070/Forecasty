package com.android.forecasty.utils

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.android.forecasty.data.model.cycle.ListItem

class CityAdapter constructor(val itemsList: MutableList<ListItem>): RecyclerView.Adapter<CityViewHolder>() {

    fun updateList(itemsList: MutableList<ListItem>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(this.itemsList, itemsList))
        this.itemsList.clear()
        this.itemsList.addAll(itemsList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
            CityViewHolder(parent)

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindTo(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size
}