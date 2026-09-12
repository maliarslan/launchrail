package com.launchrail.infrastructure.persistence.applications

import com.launchrail.application.ManagedApplicationRepository
import com.launchrail.domain.EntityId
import com.launchrail.domain.ManagedApplication
import com.launchrail.domain.ManagedApplicationId
import com.launchrail.infrastructure.persistence.DatabaseFactory.dbQuery
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll

class ExposedManagedApplicationRepository : ManagedApplicationRepository {
    override suspend fun save(application: ManagedApplication): ManagedApplication {
        return dbQuery {
            ApplicationsTable.insert {
                it[id] = application.id.value
                it[name] = application.name
                it[description] = application.description
                it[createdAt] = application.createdAt
                it[updatedAt] = application.updatedAt
            }
            application
        }
    }

    override suspend fun findById(id: ManagedApplicationId): ManagedApplication? {
        return dbQuery {
            ApplicationsTable.selectAll()
                .where { ApplicationsTable.id eq id.value }
                .singleOrNull()
                ?.toManagedApplication()
        }
    }

    private fun ResultRow.toManagedApplication(): ManagedApplication {
        return ManagedApplication(
            id = EntityId(this[ApplicationsTable.id]),
            name = this[ApplicationsTable.name],
            description = this[ApplicationsTable.description],
            createdAt = this[ApplicationsTable.createdAt],
            updatedAt = this[ApplicationsTable.updatedAt],
        )
    }
}