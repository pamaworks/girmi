import { Box, Container, Link, Typography } from '@mui/material';

function Copyright() {
	return (
		<Typography variant="body2" color="text.secondary" align="center">
			{'Copyright © '}
			<Link color="inherit" href="/">
				Your Website
			</Link>{' '}
			{`${new Date().getFullYear()}.`}
		</Typography>
	);
}

export default function Footer() {
	return (
		<Box component="footer" sx={{ bgcolor: 'background.paper', py: 6 }}>
			<Container maxWidth="lg">
				<Typography variant="h6" align="center" gutterBottom>
					Welcome!
				</Typography>
				<Typography variant="subtitle1" align="center" color="text.secondary" component="p">
					Hello everyone !!!! welcome!!!
				</Typography>
				<Copyright />
			</Container>
		</Box>
	);
}
