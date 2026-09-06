import { createBrowserRouter, Navigate } from 'react-router-dom'
import { ApplicationsPage } from '../pages/applications/ApplicationsPage'
import { ApprovalsPage } from '../pages/approvals/ApprovalsPage'
import { DashboardPage } from '../pages/dashboard/DashboardPage'
import { DeploymentsPage } from '../pages/deployments/DeploymentsPage'
import { SettingsPage } from '../pages/settings/SettingsPage'
import { AppLayout } from '../shared/layout/AppLayout'

export const router = createBrowserRouter([
  {
    element: <AppLayout />,
    children: [
      {
        index: true,
        element: <DashboardPage />,
      },
      {
        path: 'applications',
        element: <ApplicationsPage />,
      },
      {
        path: 'deployments',
        element: <DeploymentsPage />,
      },
      {
        path: 'approvals',
        element: <ApprovalsPage />,
      },
      {
        path: 'settings',
        element: <SettingsPage />,
      },
    ],
  },
  {
    path: '*',
    element: <Navigate to="/" replace />,
  },
])
