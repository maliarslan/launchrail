import { useContext } from 'react'
import { ColorModeContext } from './colorModeContext'

export const useColorMode = () => {
  const value = useContext(ColorModeContext)

  if (!value) {
    throw new Error('useColorMode must be used within ColorModeProvider')
  }

  return value
}
