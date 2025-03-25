package com.example.board_api.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.board_api.dashboard.data.repository.PostRepository
import com.example.board_api.dashboard.model.Posts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel(){
    private var _posts = MutableStateFlow<List<Posts>>(emptyList())
    var activities: StateFlow<List<Posts>> = _posts.asStateFlow()

    private var _isLoading = MutableStateFlow(false);
    var isLoading: StateFlow<Boolean> = _isLoading

    fun fetchPosts(){
        viewModelScope.launch {
                _isLoading.value=true
                _posts.value=repository.getPosts()
                _isLoading.value=false
        }
    }
}