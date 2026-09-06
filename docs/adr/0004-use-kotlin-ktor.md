# ADR 0004: Use Kotlin Ktor for the Backend API

## Context

LaunchRail needs a backend API that can expose REST endpoints, enforce authorization, coordinate deployment workflows, persist data, publish API documentation, and support integration testing.

The backend should be production-oriented but still small enough to understand clearly as the project grows.

The project also aims to strengthen backend engineering skills, so the framework should make important backend concepts visible rather than hiding too much behind framework conventions.

## Decision

LaunchRail will use Kotlin with Ktor for the backend API.

Ktor will be used to define HTTP routes, middleware, authentication, serialization, validation integration, error handling, and API infrastructure.

The backend will be structured as a modular monolith with explicit layers:

```text
api
application
domain
infrastructure
```
## Consequences
Ktor is lightweight and explicit, which makes it a good fit for learning backend architecture. It gives enough framework support for building a real API while still requiring the project to make clear decisions about routing, dependency wiring, persistence, authentication, and error handling.

Kotlin provides strong typing, null safety, data classes, sealed classes, and expressive domain modeling features that are useful for deployment state machines and structured error handling.

The main trade-off is that Ktor provides fewer default conventions than larger frameworks such as Spring Boot. This means LaunchRail must define more of its own project structure and integration patterns.

This is acceptable because the project intentionally values explicit architecture and learning over framework-generated structure.
