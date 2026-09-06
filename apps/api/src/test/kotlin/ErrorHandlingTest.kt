package com.launchrail

import com.launchrail.api.errors.ErrorResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ErrorHandlingTest {
    @Test
    fun `returns 404 for a non-existent path`() = testApplication {
        configure()

        val jsonClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = jsonClient.get("/does-not-exist")

        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals(ContentType.Application.Json, response.contentType())
        assertEquals(
            ErrorResponse(
                code = "NOT_FOUND",
                message = "The requested resource was not found",
            ),
            response.body<ErrorResponse>(),
        )
    }
}
