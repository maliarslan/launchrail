# ADR 0002: Use REST Api

## Context
LaunchRail needs an API between React frontend and Kotlin backend.

The API will support resources and workflows such as applications, environments, deployments, approvals, audit logs and deployment events.

The project should have a clear, documented HTTP contract that is easy to test, easy to inspect and suitable for OpenAPI documentation.

## Decision
LaunchRail will use a REST API for communication between the frontend and backend.

The backend will expose HTTP endpoints organized around resources and use cases, such as applications, deployments, approvals and environments.

OpenAPI will be used to document the API contract.

REST is a good fit for the initial version because most operations map naturally to resource-oriented HTTP endpoints, and it keeps the system straightforward for local development, testing and portfolio review.

## Consequences
REST keeps the API easy to understand, document, test and consume from the frontend.

The main trade-off is that some frontend views may eventually require multiple API calls. If that becomes a real performance or complexity problem, the project can revisit API composition patterns later.

GraphQL is intentionally deferred because LaunchRail does not yet need highly flexible client-driven queries, and adding GraphQL would increase backend complexity before the product requires it.