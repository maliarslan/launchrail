# LaunchRail Web

React frontend for LaunchRail.

This app is intentionally starting as a small frontend foundation. Product workflows will be added incrementally as the backend and domain model are introduced.

## Stack

- React
- TypeScript
- Vite
- React Router
- TanStack Query
- MUI
- Vitest
- React Testing Library
- Playwright later

## Source Structure

```text
src/
├── app/
├── pages/
├── features/
├── shared/
└── main.tsx
```

### `app`

Application-level setup:

- root app component
- router
- providers
- theme

### `pages`

Route-level screens. Pages compose feature and shared components.

### `features`

Business-specific frontend modules such as applications, deployments, approvals, environments, and authentication.

Feature code should contain domain-specific UI, API hooks, types, and validation once those workflows exist.

### `shared`

Reusable non-domain-specific frontend code:

- shared components
- layout
- API client setup
- common hooks
- utilities
- shared types

## Commands

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

Build the app:

```bash
npm run build
```

Run linting:

```bash
npm run lint
```

## Current Scope

The current frontend scope is application foundation only:

- app providers
- routing
- MUI theme
- light/dark theme mode
- shared layout
- placeholder pages

No product features, API integration, authentication, or authorization behavior are implemented yet.
