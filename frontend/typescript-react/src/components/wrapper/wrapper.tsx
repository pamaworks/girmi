import { ReactNode, useEffect, useState } from 'react';
import { Box, CircularProgress, Container, CssBaseline, Fade, ThemeProvider, createTheme } from '@mui/material';
import { useLocation } from 'react-router-dom';
import Footer from './footer';
import Header from './header';
import SignIn from '../sign/signIn';

function Wrapper(props: { child: ReactNode }) {
	const { child } = props;
	const defaultTheme = createTheme();
	const { pathname } = useLocation();
	const [isLoading, setIsLoading] = useState(false);
	const [isLogin, setIsLogin] = useState(false);

	function checkView() {
		return <>{!isLogin ? <SignIn /> : <>{child}</>}</>;
	}

	const showLoadingView = () => {
		return (
			<Box
				alignItems="center"
				sx={{ marginTop: 10, display: 'flex', flexDirection: 'column', alignItems: 'center', height: 100 }}
			>
				<Fade in={isLoading}>
					<CircularProgress />
				</Fade>
			</Box>
		);
	};

	const showView = () => {
		return isLoading ? showLoadingView() : checkView();
	};

	return (
		<>
			<ThemeProvider theme={defaultTheme}>
				<CssBaseline />
				<Header />
				<Container maxWidth="lg">{showView()}</Container>
				<Footer />
			</ThemeProvider>
		</>
	);
}
export default Wrapper;
