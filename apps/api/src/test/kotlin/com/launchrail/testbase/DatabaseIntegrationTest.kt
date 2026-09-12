package com.launchrail.testbase

import com.launchrail.infrastructure.persistence.DatabaseConfig
import com.launchrail.infrastructure.persistence.DatabaseFactory
import com.launchrail.infrastructure.persistence.liquibase.LiquibaseMigrator
import org.testcontainers.containers.PostgreSQLContainer

abstract class DatabaseIntegrationTest {
    init {
        startDatabase()
    }

    private companion object {
        private val postgres = PostgreSQLContainer("postgres:18-alpine")
        private var initialized = false

        fun startDatabase() {
            if (initialized) {
                return
            }

            if (!postgres.isRunning) {
                postgres.start()
            }

            val config = DatabaseConfig(
                url = postgres.jdbcUrl,
                user = postgres.username,
                password = postgres.password,
            )

            LiquibaseMigrator(config).migrate()
            DatabaseFactory.connect(config)

            initialized = true
        }
    }
}
