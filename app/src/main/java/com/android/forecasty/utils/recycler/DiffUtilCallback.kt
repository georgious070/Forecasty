package com.android.forecasty.utils.recycler

import android.support.v7.util.DiffUtil
import com.android.forecasty.data.model.cycle.ListItem

class DiffUtilCallback constructor(val oldList: MutableList<ListItem>, val newList: MutableList<ListItem>)
    : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
}