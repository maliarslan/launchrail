# ADR 0006: Use Exposed DSL for Persistence

## Context

LaunchRail will use PostgreSQL as its primary database and needs a persistence approach that works with Kotlin, keeps database access explicit and fits the modular architecture.

The database schema will evolve over time as the project adds organizations, users, applications, environments, deployments, approvals, deployment events, audit logs, indexes, constraints, and authorization-related tables.

The persistence layer must keep domain models separate from database table mappings. Database concerns should live in the infrastructure layer, while use cases depend on repository interfaces.

## Decision

LaunchRail will use Exposed DSL for database access.

Exposed will be used as a Kotlin-first SQL DSL for writing queries and mapping database rows to application/domain models.

LaunchRail will not use Exposed DAO as the primary persistence model. Domain models should remain independent of Exposed entities and table definitions.

Liquibase will remain responsible for creating and changing database schema. Exposed should map to the existing schema and execute queries, not own production schema generation.

## Alternatives Considered

### Plain JDBC

Plain JDBC provides the most direct access to SQL and database connections.

It was not selected because it would create repetitive boilerplate for query execution, parameter binding, and row mapping. This would distract from the application architecture once more entities and query patterns are introduced.

### Jdbi

Jdbi is a lightweight SQL mapper that keeps SQL explicit while reducing JDBC boilerplate.

It was not selected for the initial version because LaunchRail is a Kotlin/Ktor project, and Exposed provides a more Kotlin-native persistence experience.

### Exposed DAO

Exposed DAO provides an entity-style abstraction over database tables.

It was not selected because it can blur the boundary between persistence models and domain models. LaunchRail should keep database mapping concerns in the infrastructure layer and avoid making persistence entities the core domain model.

### jOOQ

jOOQ provides powerful type-safe SQL and code generation.

It was not selected for the initial version because it adds more setup and code-generation complexity than LaunchRail needs at this stage. It may be reconsidered later if query complexity grows significantly.

## Consequences

Exposed DSL gives LaunchRail Kotlin-friendly, type-safe database access while keeping SQL concepts visible.

Repository implementations will live in the infrastructure layer and use Exposed to query PostgreSQL.

Application and domain code should depend on repository interfaces, not Exposed table objects or database transactions directly.

Liquibase migrations must remain the source of truth for schema changes. Exposed table definitions must stay aligned with Liquibase migrations.

This decision introduces some duplication between migration files and Exposed table mappings, but that duplication is acceptable because it keeps schema ownership explicit and avoids runtime schema generation.