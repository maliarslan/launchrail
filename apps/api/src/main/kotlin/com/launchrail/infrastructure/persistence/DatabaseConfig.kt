package com.launchrail.infrastructure.persistence

import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.withFallback
import io.ktor.server.config.yaml.YamlConfig

data class DatabaseConfig(
    val url: String,
    val user: String,
    val password: String,
) {
    companion object {
        fun fromApplicationConfig(): DatabaseConfig {
            val config = loadApplicationConfig()

            return DatabaseConfig(
                url = config.requiredString("database.url"),
                user = config.requiredString("database.user"),
                password = config.requiredString("database.password"),
            )
        }

        private fun loadApplicationConfig(): ApplicationConfig {
            val baseConfig = YamlConfig("application.yaml")
                ?: error("Could not load application.yaml")
            val localConfig = YamlConfig("application-local.yaml")

            return localConfig?.withFallback(baseConfig) ?: baseConfig
        }

        private fun ApplicationConfig.requiredString(path: String): String {
            val value = property(path).getString()

            return value.ifBlank {
                error("Missing required configuration value: $path")
            }
        }
    }
}
