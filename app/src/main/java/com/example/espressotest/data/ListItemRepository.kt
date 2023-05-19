package com.example.espressotest.data

import com.example.espressotest.R


class ListItemRepository {

    fun getListItem(): ArrayList<ItemData> {
        // ArrayList of class ItemData
        val data = ArrayList<ItemData>()
        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemData(R.drawable.daisy, "User $i"))
        }
        return data
    }
}