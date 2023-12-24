import { Container, CssBaseline, ThemeProvider, createTheme } from '@mui/material';
import { BrowserRouter, Route, Routes, useLocation } from 'react-router-dom';
import Header from '../wrapper/header';
import Footer from '../wrapper/footer';
import Main from './main';
import SignIn from '../sign/signIn';
import SignUp from '../sign/signUp';

export const noShowHeaderUrls = ['/signin', '/signup'];

function MainIndex() {
	const defaultTheme = createTheme();
	return (
		<ThemeProvider theme={defaultTheme}>
			<CssBaseline />
			<Container maxWidth="lg">
				<BrowserRouter basename="/">
					<Header />
					<Routes>
						<Route path="/" element={<Main />} />
						<Route path="/signin" element={<SignIn />} />
						<Route path="/signup" element={<SignUp />} />
					</Routes>
				</BrowserRouter>
			</Container>
			<Footer />
		</ThemeProvider>
	);
}

export default MainIndex;
