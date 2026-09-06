package com.launchrail

import com.launchrail.api.health.configureHealthRoutes
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        configureHealthRoutes()
    }
}