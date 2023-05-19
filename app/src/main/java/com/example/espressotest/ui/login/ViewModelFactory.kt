package com.example.espressotest.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.espressotest.data.ListItemRepository
import com.example.espressotest.data.LoginDataSource
import com.example.espressotest.data.LoginRepository
import com.example.espressotest.ui.list.ListViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }else if(modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(
                listItemRepository = ListItemRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}