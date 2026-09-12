package com.launchrail.infrastructure.persistence.liquibase

import com.launchrail.infrastructure.persistence.DatabaseConfig
import liquibase.Contexts
import liquibase.LabelExpression
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import java.sql.DriverManager

class LiquibaseMigrator(
    private val config: DatabaseConfig,
) {
    private companion object {
        const val CHANGELOG_PATH = "db/changelog/db.changelog-master.xml"
    }

    fun migrate() {
        DriverManager.getConnection(config.url, config.user, config.password).use { connection ->
            val database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(JdbcConnection(connection))

            Liquibase(
                CHANGELOG_PATH,
                ClassLoaderResourceAccessor(),
                database,
            ).use { liquibase ->
                liquibase.update(Contexts(), LabelExpression())
            }
        }
    }
}
