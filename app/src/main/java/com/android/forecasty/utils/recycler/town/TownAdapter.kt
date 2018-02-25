package com.android.forecasty.utils.recycler.town

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.android.forecasty.data.repository.town.DayData
import com.android.forecasty.utils.recycler.DiffUtilCallback

class TownAdapter constructor(val itemsList: MutableList<DayData>) : RecyclerView.Adapter<TownViewHolder>() {

    fun updateList(itemsList: MutableList<DayData>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(this.itemsList, itemsList))
        this.itemsList.clear()
        this.itemsList.addAll(itemsList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TownViewHolder =
            TownViewHolder(parent)

    override fun onBindViewHolder(holder: TownViewHolder, position: Int) {
        holder.bindTo(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size
}