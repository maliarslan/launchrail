import type { PaletteMode } from '@mui/material'
import { createTheme } from '@mui/material/styles'

export const createAppTheme = (mode: PaletteMode) =>
  createTheme({
    palette: {
      mode,
      primary: {
        main: '#1f5eff',
      },
      background: {
        default: mode === 'light' ? '#f6f8fb' : '#101418',
        paper: mode === 'light' ? '#ffffff' : '#171c22',
      },
    },
    typography: {
      fontFamily:
        'Inter, ui-sans-serif, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif',
      h1: {
        fontSize: '2rem',
        fontWeight: 700,
        letterSpacing: 0,
      },
      h2: {
        fontSize: '1.375rem',
        fontWeight: 700,
        letterSpacing: 0,
      },
    },
    shape: {
      borderRadius: 8,
    },
  })
