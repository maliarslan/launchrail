# LaunchRail

LaunchRail is a portfolio-grade internal developer platform for managing application deployments and releases across environments such as staging and production.

The project is intentionally built as a production-oriented modular monolith. It is designed to demonstrate full-stack engineering across frontend, backend, persistence, infrastructure, CI/CD, and observability.

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