package com.example.espressotest.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.espressotest.data.ItemData
import com.example.espressotest.data.ListItemRepository

class ListViewModel(private val listItemRepository: ListItemRepository)  : ViewModel() {

    private val _itemDataList = MutableLiveData<ArrayList<ItemData>>()
    val itemDataList: LiveData<ArrayList<ItemData>> = _itemDataList

    fun getData(){
        val list = listItemRepository.getListItem()

        if (list.isNotEmpty()){
            _itemDataList.value = list
        }
    }
}