package com.launchrail.infrastructure.persistence

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import kotlin.coroutines.CoroutineContext

object DatabaseFactory {
    fun connect(config: DatabaseConfig) {
        Database.connect(
            driver = "org.postgresql.Driver",
            url = config.url,
            user = config.user,
            password = config.password,
        )
    }

    suspend fun <T> dbQuery(
        context: CoroutineContext = Dispatchers.IO,
        block: () -> T,
    ): T {
        return withContext(context) {
            transaction {
                block()
            }
        }
    }
}