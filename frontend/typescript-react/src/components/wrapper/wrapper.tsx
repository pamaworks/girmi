import { ReactNode, useEffect, useState } from 'react';
import { Box, CircularProgress, Container, CssBaseline, Fade, ThemeProvider, createTheme } from '@mui/material';
import { useLocation } from 'react-router-dom';
import Footer from './footer';
import Header from './header';
import { needLoginUrls } from '../routers';
import SignIn from '../sign/signIn';

function Wrapper(props: { child: ReactNode }) {
	const { child } = props;
	const defaultTheme = createTheme();
	const { pathname } = useLocation();
	const [isLoading, setIsLoading] = useState(false);
	const [isLogin, setIsLogin] = useState(false);

	function checkLogin() {
		if (needLoginUrls.includes(pathname)) {
			setIsLogin(false);
		} else {
			setIsLogin(true);
		}
		setIsLoading(false);
	}

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

	useEffect(() => {
		checkLogin();
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

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
