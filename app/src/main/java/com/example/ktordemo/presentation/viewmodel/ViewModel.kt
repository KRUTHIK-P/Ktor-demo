package com.example.ktordemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktordemo.data.model.Post
import com.example.ktordemo.domain.usecase.UseCase
import com.example.ktordemo.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ViewModel(private val useCase: UseCase = UseCase()) : ViewModel() {

    private val _post: MutableStateFlow<NetworkResult<List<Post>>> =
        MutableStateFlow(NetworkResult.Loading)
    val post: StateFlow<NetworkResult<List<Post>>> = _post

    init {
        performGet()
        performPost()
        performPatch()
        performPut()
        performDelete()
        performGetComments()
    }

    private fun performGet() {
        viewModelScope.launch {
            useCase.get()
                .flowOn(Dispatchers.IO)
                .collect {
                    _post.value = it
                }
        }
    }

    private fun performPost() {
        viewModelScope.launch {
            useCase.post(
                Post(
                    body = "body",
                    id = 1,
                    title = "title",
                    userId = 3
                )
            )
        }
    }

    private fun performPatch() {
        viewModelScope.launch {
            useCase.patch(mapOf("title" to "abc"), 1)
        }
    }

    private fun performPut() {
        viewModelScope.launch {
            useCase.put(
                Post(
                    body = "body",
                    id = 1,
                    title = "title",
                    userId = 3
                ), 1
            )
        }
    }

    private fun performDelete() {
        viewModelScope.launch {
            useCase.delete(1)
        }
    }

    private fun performGetComments() {
        viewModelScope.launch {
            useCase.getComments(1)
        }
    }
}