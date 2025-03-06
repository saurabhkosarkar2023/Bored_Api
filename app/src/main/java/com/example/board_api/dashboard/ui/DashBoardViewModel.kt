package com.example.board_api.dashboard.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.board_api.dashboard.data.RetrofitClient
import com.example.board_api.dashboard.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashBoardViewModel : ViewModel(){
    private val _activity = MutableStateFlow<List<Todo>>(emptyList())
    val activities: StateFlow<List<Todo>> = _activity

    init {
        fetchTodo()
    }

    private fun fetchTodo(){
        viewModelScope.launch {
            try {
                val todo=RetrofitClient.apiService.getTodo()
                _activity.value=todo
            }
            catch (e: Exception){
                Log.d("Network_Exception","Exception while fetching data")
            }
        }
    }
}