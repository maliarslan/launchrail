import { Alert, Box, Grid, Paper, Stack, Typography } from '@mui/material'

const summaryItems = [
  { label: 'Applications', value: '4' },
  { label: 'Active deployments', value: '2' },
  { label: 'Pending approvals', value: '1' },
]

export const DashboardPage = () => {
  return (
    <Stack spacing={3}>
      <Box>
        <Typography variant="h1">Dashboard</Typography>
        <Typography color="text.secondary">
          Operational overview for applications, deployments, and approvals.
        </Typography>
      </Box>

      <Grid container spacing={2}>
        {summaryItems.map((item) => (
          <Grid key={item.label} size={{ xs: 12, md: 4 }}>
            <Paper variant="outlined" sx={{ p: 2 }}>
              <Typography color="text.secondary" variant="body2">
                {item.label}
              </Typography>
              <Typography variant="h2">{item.value}</Typography>
            </Paper>
          </Grid>
        ))}
      </Grid>

      <Alert severity="info">
        This screen is a frontend foundation placeholder. Product workflows will
        be introduced in later milestones.
      </Alert>
    </Stack>
  )
}
