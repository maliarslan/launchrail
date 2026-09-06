# ADR 0001: Use Modular Monolith Architecture

## Context
LaunchRail is an internal developer platform for managing applications, deployments, approvals and release history.

The system need more structure than a simple CRUD application because it will eventually include deployment lifecycle rules, production approval rules, role-based authorization, audit logging, persistence, observability, and infrastructure automation.

## Decision
LaunchRail will start as a modular monolith.

The backend will be deployed as one Kotlin ktor application, but its code will be organized into clear internal leyers:
```text
api
application
domain
infrastructure
```
The `api` layer handles HTTP concerns. The `application` layer coordinates use cases. The `domain` layer contains business rules. The `infrastructure` layer connects the application to external systems such as PostgreSQL, Liquibase, authentication, metrics, and tracing.
This structure keeps the system simple to run and deploy while still making the codebase easier to evolve as the domain grows.

## Consequences
This decision keeps local development, testing, deployment, and debugging simpler than a microservice architecture.
It also makes backend boundaries explicit, which helps prevent business rules from being spread across route handlers, database code, or frontend logic.
The main trade-off is that all backend modules are deployed together. If LaunchRail later needs independently scalable services or separate team ownership, some modules may need to be extracted into services.
For now, extraction is intentionally deferred until the domain boundaries are clearer and the operational cost is justified.