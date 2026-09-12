package com.launchrail.domain

import java.util.UUID

@JvmInline
value class EntityId<T>(val value: UUID)