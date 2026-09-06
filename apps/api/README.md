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

## Current Scope

The current API scope is foundation only:

- application startup through Ktor `LaunchRail`
- YAML-based runtime configuration
- JSON serialization
- centralized error response baseline
- health endpoint
- basic endpoint test

No database, Liquibase migrations, authentication, authorization, deployment workflows, or product entities are implemented yet.

## Source Structure

```text
src/main/kotlin/com/launchrail/
├── Application.kt
├── Routing.kt
└── api/
    ├── Serialization.kt
    ├── errors/
    │   └── ErrorHandling.kt
    └── health/
        └── HealthRoutes.kt
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

## Commands

Run tests:

```bash
./gradlew test
```

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
