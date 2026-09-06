import { CssBaseline, ThemeProvider } from '@mui/material'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { useMemo, type ReactNode } from 'react'
import { ColorModeProvider } from './ColorModeProvider'
import { createAppTheme } from './theme'
import { useColorMode } from './useColorMode'

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: 1,
    },
  },
})

type AppProvidersProps = {
  children: ReactNode
}

export const AppProviders = ({ children }: AppProvidersProps) => {
  return (
    <QueryClientProvider client={queryClient}>
      <ColorModeProvider>
        <ThemedApp>{children}</ThemedApp>
      </ColorModeProvider>
    </QueryClientProvider>
  )
}

const ThemedApp = ({ children }: AppProvidersProps) => {
  const { mode } = useColorMode()
  const theme = useMemo(() => createAppTheme(mode), [mode])

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      {children}
    </ThemeProvider>
  )
}
