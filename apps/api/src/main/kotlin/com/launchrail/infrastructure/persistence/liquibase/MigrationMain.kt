package com.launchrail.infrastructure.persistence.liquibase

import com.launchrail.infrastructure.persistence.DatabaseConfig

fun main() {
    val config = DatabaseConfig.fromApplicationConfig()

    LiquibaseMigrator(config).migrate()

    println("Database migrations completed.")
}
