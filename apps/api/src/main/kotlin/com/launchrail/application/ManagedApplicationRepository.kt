package com.launchrail.application

import com.launchrail.domain.ManagedApplication
import com.launchrail.domain.ManagedApplicationId

interface ManagedApplicationRepository {
    suspend fun save(application: ManagedApplication): ManagedApplication
    suspend fun findById(id: ManagedApplicationId): ManagedApplication?
}