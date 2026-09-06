# Architecture Overview

LaunchRail is a modular monolith for managing application deployments, release approvals, deployment history, and environment state.

The system starts as two deployable applications:

- `apps/web`: React frontend
- `apps/api`: Kotlin Ktor backend

Supporting infrastructure lives under `infrastructure/`.

## Architectural Style

LaunchRail uses a modular monolith architecture.

This means the backend is deployed as one application, but the code is organized around clear internal boundaries. The goal is to keep the system simple to operate while avoiding a tightly coupled codebase.

LaunchRail does not start as microservices. The domain is not large enough yet to justify independent services, distributed transactions, service discovery, or cross-service observability.

## Backend Layers

The backend is organized into four main layers:

```text
api
application
domain
infrastructure
```

### api

The `api` layer handles HTTP concerns.

Responsibilities:

- define routes
- receive requests
- validate request shape
- map request DTOs to application commands
- map application results to response DTOs
- return HTTP status codes
- expose OpenAPI documentation

This layer should not contain business rules.

### application

The `application` layer coordinates use cases.

Responsibilities:

- execute workflows such as creating or approving deployments
- enforce authorization decisions at use-case boundaries
- manage transaction boundaries
- call domain logic
- call repository interfaces
- coordinate audit logging and events

Application code should describe what the system does, not how data is stored or transported over HTTP.

### domain

The `domain` layer contains business rules and core models.

Responsibilities:

- model concepts such as Application, Environment, Deployment, Approval, and AuditLog
- enforce deployment lifecycle rules
- enforce state transitions
- represent domain-specific errors
- keep business behavior independent from frameworks

Domain code should not depend on Ktor, PostgreSQL, Liquibase, JSON, or HTTP.

### infrastructure

The `infrastructure` layer connects the application to external systems.

Responsibilities:

- PostgreSQL persistence
- repository implementations
- Liquibase migrations
- authentication provider integration
- metrics and tracing
- configuration
- external service clients if needed

Infrastructure code can depend on frameworks and libraries. Other layers should avoid depending directly on infrastructure details.

## Frontend Structure

The frontend is organized around user-facing workflows:

```text
app
pages
features
shared
```

- `app`: router setup, providers, theme, query client, global layout
- `pages`: route-level screens
- `features`: business modules such as applications, deployments, approvals, environments, authentication
- `shared`: reusable UI components, API client, formatting utilities, common hooks, shared types

## Initial Domain Concepts

LaunchRail starts with these core concepts:

- Organization
- User
- Application
- Environment
- Deployment
- DeploymentEvent
- Approval
- AuditLog

The first meaningful domain behavior will be deployment lifecycle management.

Successful lifecycle:

```text
CREATED
-> PENDING_APPROVAL
-> APPROVED
-> DEPLOYING
-> DEPLOYED
```

Failure path:

```text
DEPLOYING
-> FAILED
```

## Authorization Boundary

Authorization must be enforced server-side.

Frontend authorization can improve user experience by hiding unavailable actions, but it is not a security boundary.

Initial roles:

- DEVELOPER
- RELEASE_MANAGER
- ADMIN

Example rules:

- Developers can deploy to staging.
- Production deployment requires approval.
- Release Managers can approve production deployments.
- Admins can manage applications and environments.

## Documentation Strategy

Major technical decisions are recorded as Architecture Decision Records under `docs/adr`.

Initial ADRs:

- modular monolith architecture
- REST API
- PostgreSQL
- Kotlin Ktor
- Liquibase migrations
- authentication strategy
- deployment status updates with SSE or WebSockets
