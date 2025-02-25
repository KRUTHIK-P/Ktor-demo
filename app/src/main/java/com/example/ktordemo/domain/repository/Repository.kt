package com.example.ktordemo.domain.repository

import com.example.ktordemo.data.model.Post
import com.example.ktordemo.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun get(): Flow<NetworkResult<List<Post>>>

    suspend fun post(post: Post)

    suspend fun patch(field: Map<String, String>, id: Int)

    suspend fun put(post: Post, id: Int)

    suspend fun delete(id: Int)

    suspend fun getComments(id: Int)
}