package com.example.ktordemo.data.repository

import android.util.Log
import com.example.ktordemo.data.client.KtorClient
import com.example.ktordemo.data.model.Comment
import com.example.ktordemo.data.model.Post
import com.example.ktordemo.domain.repository.Repository
import com.example.ktordemo.utils.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RepositoryImpl(private val client: HttpClient = KtorClient.client) : Repository {

    override suspend fun get(): Flow<NetworkResult<List<Post>>> = flow {
        emit(NetworkResult.Loading)
        try {
            val response = client.get("/posts").body<List<Post>>()
            Log.d("apiCall", "GET: $response")
            emit(NetworkResult.Success(response))
        } catch (e: ClientRequestException) {
            // handle 4xx
            Log.d("apiCall", "GET error: $e")
            emit(NetworkResult.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: ServerResponseException) {
            Log.d("apiCall", "GET error: $e")
            emit(NetworkResult.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: Exception) {
            Log.d("apiCall", "GET error: $e")
            emit(NetworkResult.Error(e.localizedMessage ?: "An error occurred"))
        }
    }.catch { e ->
        emit(NetworkResult.Error(e.localizedMessage ?: "An error occurred"))
    }

    override suspend fun post(post: Post) {
        try {
            val response = client.post {
                url("/posts")
                contentType(ContentType.Application.Json)
                setBody(post)
            }.body<Post>()
            Log.d("apiCall", "POST: $response")
        } catch (e: Exception) {
            Log.d("apiCall", "POST error: $e")
        }
    }

    override suspend fun patch(field: Map<String, String>, id: Int) {
        try {
            val response = client.patch {
                url {
                    path("/posts/${id}")
                    contentType(ContentType.Application.Json)
                    setBody(field)
                }
            }.body<Post>()
            Log.d("apiCall", "PATCH: $response")
        } catch (e: Exception) {
            Log.d("apiCall", "PATCH error: $e")
        }
    }

    override suspend fun put(post: Post, id: Int) {
        try {
            val response = client.put {
                url {
                    path("/posts/${id}")
                    contentType(ContentType.Application.Json)
                    setBody(post)
                }
            }.body<Post>()
            Log.d("apiCall", "PUT $response")
        } catch (e: ClientRequestException) {
            // handle 4xx
            Log.d("apiCall", "PUT error: $e")
        } catch (e: ServerResponseException) {
            Log.d("apiCall", "PUT error: $e")
        } catch (e: Exception) {
            Log.d("apiCall", "PUT error: $e")
        }
    }

    override suspend fun delete(id: Int) {
        try {
            val response = client.delete("/posts/$id").status
            Log.d("apiCall", "DELETE $response")
        } catch (e: ClientRequestException) {
            // handle 4xx
            Log.d("apiCall", "DELETE error: $e")
        } catch (e: ServerResponseException) {
            Log.d("apiCall", "DELETE error: $e")
        } catch (e: Exception) {
            Log.d("apiCall", "DELETE error: $e")
        }
    }

    override suspend fun getComments(id: Int) {
        try {
            val response = client.get {
                url {
                    path("/comments")
                    parameter("postId", id)
                }
            }.body<List<Comment>>()
            Log.d("apiCall", "COMMENT $response")
        } catch (e: ClientRequestException) {
            // handle 4xx
            Log.d("apiCall", "COMMENT error: $e")
        } catch (e: ServerResponseException) {
            Log.d("apiCall", "COMMENT error: $e")
        } catch (e: Exception) {
            Log.d("apiCall", "COMMENT error: $e")
        }
    }
}