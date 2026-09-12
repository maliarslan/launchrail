# LaunchRail API

Kotlin Ktor backend for LaunchRail.

This app starts as the backend foundation for the LaunchRail modular monolith. Product workflows, persistence, authentication, authorization, and deployment domain behavior will be added incrementally in later milestones.

## Stack

- Kotlin
- Ktor
- Netty
- Gradle
- YAML configuration
- kotlinx.serialization
- JUnit/Kotlin test
- Ktor test host
- PostgreSQL
- Liquibase
- Exposed DSL
- Testcontainers

## Current Scope

The current API scope includes the backend foundation and first persistence slice:

- application startup through Ktor
- YAML-based runtime configuration
- JSON serialization
- centralized error response baseline
- health endpoint
- PostgreSQL local development database
- Liquibase migration CLI
- first `applications` table migration
- `ManagedApplication` domain model
- repository interface and Exposed repository implementation
- Testcontainers-backed repository integration test

Authentication, authorization, deployment workflows, and product API endpoints are not implemented yet.

## Source Structure

```text
src/main/kotlin/com/launchrail/
├── Application.kt
├── Routing.kt
├── application/
│   └── ManagedApplicationRepository.kt
├── api/
│   ├── Serialization.kt
│   ├── errors/
│   │   └── ErrorHandling.kt
│   └── health/
│       └── HealthRoutes.kt
├── domain/
│   ├── EntityId.kt
│   └── ManagedApplication.kt
└── infrastructure/
    └── persistence/
        ├── DatabaseConfig.kt
        ├── DatabaseFactory.kt
        ├── applications/
        │   ├── ApplicationsTable.kt
        │   └── ExposedManagedApplicationRepository.kt
        └── liquibase/
            ├── LiquibaseMigrator.kt
            └── MigrationMain.kt
```

### `Application.kt`

Composition root for the Ktor application.

This is where application-level plugins and routes are wired together.

### `Routing.kt`

Registers HTTP route groups.

For now, it only connects the health route. Later route groups such as applications, deployments, approvals, and environments will be added here.

### `api/Serialization.kt`

Installs Ktor content negotiation and JSON serialization.

### `api/errors/ErrorHandling.kt`

Installs centralized error handling and defines the baseline API error response shape.

### `api/health/HealthRoutes.kt`

Defines the technical health endpoint:

```http
GET /health
```

### `domain`

Contains framework-independent domain models such as `ManagedApplication`.

### `application`

Contains application-layer contracts such as `ManagedApplicationRepository`.

### `infrastructure/persistence`

Contains database configuration, Exposed table mappings, repository implementations, and Liquibase migration tooling.

## Runtime Configuration

The application uses YAML configuration:

```text
src/main/resources/application.yaml
```

Gradle runs Ktor's generic Netty engine:

```kotlin
application {
    mainClass = "io.ktor.server.netty.EngineMain"
}
```

`EngineMain` reads `application.yaml`, then loads the LaunchRail module:

```yaml
ktor:
  application:
    modules:
      - com.launchrail.ApplicationKt.module
```

Local database settings are read from:

```text
src/main/resources/application-local.yaml
```

This file is ignored by Git. Use the example file as a template:

```bash
cp src/main/resources/application-local.example.yaml src/main/resources/application-local.yaml
```

Example local database config:

```yaml
database:
  url: jdbc:postgresql://localhost:5432/launchrail
  user: launchrail
  password: launchrail
```

## PostgreSQL And Migrations

Start the local PostgreSQL database from the repository root:

```bash
docker compose up -d postgres
```

Run Liquibase migrations from `apps/api`:

```bash
./gradlew runMigrations
```

The migration command is separate from API startup. The API assumes the database schema already exists.

Current master changelog:

```text
src/main/resources/db/changelog/db.changelog-master.xml
```

Current migration:

```text
src/main/resources/db/changelog/changes/0001-create-applications-table.xml
```

## OpenAPI Direction

LaunchRail will expose an OpenAPI contract for its REST API.

OpenAPI is not implemented in the foundation milestone yet. The first version of the API only includes technical endpoints such as `/health`.

Before adding product endpoints, the project should choose how OpenAPI will be generated or maintained.

Options to evaluate:

- generate OpenAPI from Ktor route definitions and typed request/response models
- maintain an OpenAPI specification manually
- use contract-first API design and generate server/client types from the specification

The preferred direction is to keep OpenAPI close to the backend implementation while avoiding duplicated route definitions.

This decision should be recorded in an ADR before the first product API endpoints are introduced.

## Commands

Run tests:

```bash
./gradlew test
```

The test suite includes:

- Ktor endpoint tests for `/health` and structured error responses
- Testcontainers integration tests for PostgreSQL persistence

Testcontainers uses Docker. If Docker Desktop uses a newer Docker API, the test resources include:

```text
src/test/resources/docker-java.properties
```

to pin the Docker Java client API version used during tests.

Build the API:

```bash
./gradlew build
```

Run the server:

```bash
./gradlew run
```

When the server is running, check the health endpoint:

```bash
curl http://localhost:8080/health
```

Expected response:

```json
{
  "status": "UP"
}
```

## IntelliJ IDEA Run Configuration

Prefer running the API through a provided run configuration under `/.run`
