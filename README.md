# LaunchRail

LaunchRail is a learning and portfolio project: an internal developer platform for managing application deployments and releases across environments such as staging and production.

It is not a commercial product. The goal is to demonstrate production-oriented full-stack engineering across frontend, backend, persistence, infrastructure, CI/CD, and observability.

The project is intentionally built as a modular monolith to keep the system realistic while staying focused enough for a solo learning project.

## Project Goals

- Practice building a production-style full-stack application end to end
- Demonstrate backend architecture, domain modeling, persistence, and testing
- Demonstrate frontend application structure, routing, UI composition, and API integration
- Explore infrastructure topics such as Docker, CI/CD, Kubernetes, and observability
- Keep the scope realistic for a portfolio project rather than a commercial product

## Tech Stack

### Frontend

- React
- TypeScript
- Vite
- React Router
- TanStack Query
- MUI
- Vitest
- React Testing Library
- Playwright

### Backend

- Kotlin
- Ktor
- REST API
- OpenAPI
- JUnit
- Testcontainers

### Data

- PostgreSQL
- Liquibase

### Infrastructure

- Docker
- Docker Compose
- Kubernetes
- Helm
- GitHub Actions
- Prometheus
- Grafana
- OpenTelemetry

## Architecture

LaunchRail starts as a modular monolith.

Backend layers:

- `api`: HTTP routes, DTOs, validation, error mapping
- `application`: use cases and orchestration
- `domain`: business rules and domain models
- `infrastructure`: database, migrations, external integrations, observability

Frontend areas:

- `app`: application bootstrap and providers
- `pages`: route-level screens
- `features`: business features
- `shared`: reusable UI and utilities

## Development Milestones

1. Repository and architecture
2. React application foundation
3. Kotlin API foundation
4. PostgreSQL persistence
5. Authentication and authorization
6. Deployment domain
7. Testing
8. Docker
9. CI/CD
10. Kubernetes
11. Observability
12. Production hardening
