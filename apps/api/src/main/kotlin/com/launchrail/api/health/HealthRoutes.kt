package com.launchrail.api.health

import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.serialization.Serializable

@Serializable
data class HealthResponse(val status: String)

fun Route.configureHealthRoutes() {
    route("/health") {
        get { call.respond(HealthResponse("UP")) }
    }
}