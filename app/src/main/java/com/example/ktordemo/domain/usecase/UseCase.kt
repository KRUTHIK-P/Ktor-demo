package com.example.ktordemo.domain.usecase

import com.example.ktordemo.data.model.Post
import com.example.ktordemo.data.repository.RepositoryImpl
import com.example.ktordemo.domain.repository.Repository

class UseCase(private val repository: Repository = RepositoryImpl()) {

    suspend fun get() = repository.get()

    suspend fun post(post: Post) {
        repository.post(post)
    }

    suspend fun patch(field: Map<String, String>, id: Int) {
        repository.patch(field, id)
    }

    suspend fun put(post: Post, id: Int) {
        repository.put(post, id)
    }

    suspend fun delete(id: Int) {
        repository.delete(id)
    }

    suspend fun getComments(id: Int) {
        repository.getComments(id)
    }

    suspend fun uploadImage(
        byteArray: ByteArray,
        fileName: String,
        fileType: String,
        fieldName: String
    ) {
        repository.uploadImage(byteArray, fileName, fileType, fieldName)
    }
}