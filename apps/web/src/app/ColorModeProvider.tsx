import type { PaletteMode } from '@mui/material'
import { useEffect, useMemo, useState, type ReactNode } from 'react'
import { ColorModeContext } from './colorModeContext'

const storageKey = 'launchrail-color-mode'

const getInitialMode = (): PaletteMode => {
  const savedMode = window.localStorage.getItem(storageKey)

  if (savedMode === 'light' || savedMode === 'dark') {
    return savedMode
  }

  return window.matchMedia('(prefers-color-scheme: dark)').matches
    ? 'dark'
    : 'light'
}

type ColorModeProviderProps = {
  children: ReactNode
}

export const ColorModeProvider = ({ children }: ColorModeProviderProps) => {
  const [mode, setMode] = useState<PaletteMode>(getInitialMode)

  useEffect(() => {
    window.localStorage.setItem(storageKey, mode)
  }, [mode])

  const value = useMemo(
    () => ({
      mode,
      toggleColorMode: () => {
        setMode((currentMode) =>
          currentMode === 'light' ? 'dark' : 'light',
        )
      },
    }),
    [mode],
  )

  return (
    <ColorModeContext.Provider value={value}>
      {children}
    </ColorModeContext.Provider>
  )
}
