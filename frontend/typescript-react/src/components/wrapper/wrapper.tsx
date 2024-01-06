import { ReactNode } from 'react';
import { Container, CssBaseline, ThemeProvider, createTheme } from '@mui/material';
import Footer from './footer';
import Header from './header';

function Wrapper(props: { child: ReactNode }) {
	const { child } = props;
	const defaultTheme = createTheme();
	return (
		<>
			<ThemeProvider theme={defaultTheme}>
				<CssBaseline />
				<Header />
				<Container maxWidth="lg">{child}</Container>
				<Footer />
			</ThemeProvider>
		</>
	);
}
export default Wrapper;
