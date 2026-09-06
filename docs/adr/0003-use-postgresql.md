# ADR 0003: Use PostgreSQL as Primary Database

## Context
LaunchRail needs persistent storage for organizations, users, applications, environments, deployments, approvals, deployment events, and audit logs.
The data model has clear relationships. For example, an organization owns applications, applications have environments, deployments target environments and approvals are associated with production deployment workflows.
The system will also need reliable querying for deployment history, audit trails, filtering, sorting, pagination and reporting-style views.

## Decision
LaunchRail will use PostgreSQL as the primary database.

PostgreSQL will store the core relational data for the application, including users, applications, environments, deployments, approvals, deployment events, and audit logs.

Schema changes will be managed through database migrations rather than manual database edits.
## Consequences
PostgreSQL is a good fit because LaunchRail has relational data, consistency requirements, and query patterns that benefit from a mature relational database.

This decision supports constraints, indexes, transactions, optimistic locking, and reliable audit history.

The main trade-off is that the project must manage database schema evolution carefully. Migrations, local database setup, and integration tests become part of the development workflow.

A document database is intentionally not used for the primary data model because the core LaunchRail entities have strong relationships and consistency requirements.
