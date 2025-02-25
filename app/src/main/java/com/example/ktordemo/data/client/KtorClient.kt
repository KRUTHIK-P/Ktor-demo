package com.example.ktordemo.data.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.headers
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            socketTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT
        }

        install(DefaultRequest) {
            url {
                host = BASE_URL
                protocol = URLProtocol.HTTPS
                headers {
                    append(HttpHeaders.Authorization, "auth")
                    appendAll(
                        headersOf(
                            HttpHeaders.ContentType to listOf("application/json"),
                            HttpHeaders.Accept to listOf("application/json")
                        )
                    )
                }
            }
        }

    }

    private const val TIMEOUT: Long = 3000
    private const val BASE_URL = "jsonplaceholder.typicode.com"
}