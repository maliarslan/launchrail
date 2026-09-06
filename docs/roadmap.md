# Roadmap

LaunchRail will be built incrementally in milestones. Each milestone should be small enough to review, understand, test, and commit cleanly.

## Milestone 01: Repository and Architecture

Goal: establish the repository structure, architecture direction, documentation baseline, and initial technical decisions.

Scope:

- define project purpose in `README.md`
- document architecture in `docs/architecture/overview.md`
- add initial Architecture Decision Records
- define local development prerequisites
- define branching and commit strategy

Out of scope:

- product features
- frontend application implementation
- backend API implementation
- database schema
- authentication
- Docker Compose
- Kubernetes
- CI/CD
- observability

## Milestone 02: React Application Foundation

Goal: create the frontend application foundation without product complexity.

Expected scope:

- Vite React TypeScript setup
- React Router
- TanStack Query
- MUI theme
- base application layout
- placeholder pages
- test setup with Vitest and React Testing Library

## Milestone 03: Kotlin API Foundation

Goal: create the backend API foundation without domain complexity.

Expected scope:

- Ktor project setup
- health endpoint
- structured error response baseline
- application configuration
- basic test setup
- OpenAPI direction

## Milestone 04: PostgreSQL Persistence

Goal: introduce PostgreSQL and database migration workflow.

Expected scope:

- Docker Compose PostgreSQL
- Liquibase setup
- first schema migration
- repository pattern
- Testcontainers integration tests

## Milestone 05: Authentication and Authorization

Goal: introduce identity and server-side role-based authorization.

Expected scope:

- authentication strategy
- user model
- roles
- protected endpoints
- frontend auth flow
- authorization tests

## Milestone 06: Deployment Domain

Goal: implement the first real LaunchRail domain workflows.

Expected scope:

- applications
- environments
- deployments
- deployment lifecycle
- production approval flow
- deployment events
- audit logging

## Later Milestones

- testing depth
- Docker
- CI/CD
- Kubernetes
- Helm
- observability
- production hardening