# ADR 0005: Use Liquibase for Database Migrations

## Context

LaunchRail will use PostgreSQL as its primary database.

The database schema will evolve over time as the project adds organizations, users, applications, environments, deployments, approvals, deployment events, audit logs, indexes, constraints, and authorization-related tables.

Schema changes need to be repeatable, reviewable, and safe to apply across local development, tests, and future deployment environments.

## Decision

LaunchRail will use Liquibase to manage database schema migrations.

Database changes will be defined as versioned migration files and committed to the repository.

The backend and local development environment will apply migrations in a controlled way rather than relying on manual database edits.

## Consequences

Liquibase makes schema changes explicit and reviewable. This helps keep local development, integration tests, and deployed environments aligned.

It also supports rollback planning, checksums, and database change tracking, which are useful in production-oriented systems.

The main trade-off is that every schema change requires a migration file. This adds some overhead, but it is realistic for a backend project that cares about reliable persistence.

The project should avoid ad hoc manual schema changes because they are hard to reproduce and can cause differences between environments.