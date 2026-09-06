import {
  AppBar,
  Box,
  Button,
  Container,
  FormControlLabel,
  Switch,
  Toolbar,
  Typography,
} from '@mui/material'
import { NavLink, Outlet } from 'react-router-dom'
import { useColorMode } from '../../app/useColorMode'

const navItems = [
  { label: 'Dashboard', to: '/' },
  { label: 'Applications', to: '/applications' },
  { label: 'Deployments', to: '/deployments' },
  { label: 'Approvals', to: '/approvals' },
  { label: 'Settings', to: '/settings' },
]

export const AppLayout = () => {
  const { mode, toggleColorMode } = useColorMode()

  return (
    <Box sx={{ minHeight: '100vh' }}>
      <AppBar color="inherit" elevation={0} position="sticky">
        <Toolbar sx={{ borderBottom: 1, borderColor: 'divider', gap: 3 }}>
          <Typography component="div" sx={{ fontWeight: 800 }} variant="h6">
            LaunchRail
          </Typography>
          <Box component="nav" sx={{ display: 'flex', gap: 1 }}>
            {navItems.map((item) => (
              <Button
                key={item.to}
                color="inherit"
                component={NavLink}
                sx={{
                  '&.active': {
                    bgcolor: 'action.selected',
                    color: 'primary.main',
                  },
                }}
                to={item.to}
              >
                {item.label}
              </Button>
            ))}
          </Box>
          <Box sx={{ flexGrow: 1 }} />
          <FormControlLabel
            control={
              <Switch
                checked={mode === 'dark'}
                color="primary"
                onChange={toggleColorMode}
              />
            }
            label="Dark mode"
          />
        </Toolbar>
      </AppBar>

      <Container component="main" maxWidth="lg" sx={{ py: 4 }}>
        <Outlet />
      </Container>
    </Box>
  )
}
