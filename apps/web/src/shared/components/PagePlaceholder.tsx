import { Paper, Stack, Typography } from '@mui/material'

type PagePlaceholderProps = {
  title: string
  description: string
}

export const PagePlaceholder = ({
  title,
  description,
}: PagePlaceholderProps) => {
  return (
    <Stack spacing={2}>
      <Typography variant="h1">{title}</Typography>
      <Paper variant="outlined" sx={{ p: 3 }}>
        <Typography color="text.secondary">{description}</Typography>
      </Paper>
    </Stack>
  )
}
