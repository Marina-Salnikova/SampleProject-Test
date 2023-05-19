package com.example.espressotest.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.espressotest.R
import com.example.espressotest.ui.login.ViewModelFactory


class ListActivity : AppCompatActivity() {
    private lateinit var customAdapter: ListAdapter
    private lateinit var listViewModel: ListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listViewModel = ViewModelProvider(this, ViewModelFactory())[ListViewModel::class.java]
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        listViewModel.itemDataList.observe(this@ListActivity) {
            customAdapter.mList = it
        }
        listViewModel.getData()
        // creating Adapter
        customAdapter = ListAdapter()
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = customAdapter
    }
}