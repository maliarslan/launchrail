package com.launchrail.domain

import kotlin.time.Instant

typealias ManagedApplicationId = EntityId<ManagedApplication>

data class ManagedApplication(
    val id: ManagedApplicationId,
    val name: String,
    val description: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
)