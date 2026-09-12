package com.launchrail.infrastructure.persistence.applications

import com.launchrail.testbase.DatabaseIntegrationTest
import com.launchrail.domain.EntityId
import com.launchrail.domain.ManagedApplication
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertNotNull

class ExposedManagedApplicationRepositoryTest : DatabaseIntegrationTest() {
    @Test
    fun `saves and finds managed application by id`() = runBlocking {
        val repository = ExposedManagedApplicationRepository()
        val now = Instant.parse("2026-09-12T10:15:30Z")
        val application = ManagedApplication(
            id = EntityId(UUID.randomUUID()),
            name = "Payments API",
            description = "Handles payment authorization and capture.",
            createdAt = now,
            updatedAt = now,
        )

        repository.save(application)

        val found = repository.findById(application.id)

        assertNotNull(found)
        assertEquals(application, found)
    }

    @Test
    fun `findById returns null when application does not exist`() = runBlocking {
        val repository = ExposedManagedApplicationRepository()
        val response = repository.findById(EntityId(UUID.randomUUID()))

        assertNull(response)
    }
}
