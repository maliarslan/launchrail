package com.launchrail

import com.launchrail.api.configureSerialization
import com.launchrail.api.errors.configureErrorHandling
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureErrorHandling()
    configureSerialization()
    configureRouting()
}