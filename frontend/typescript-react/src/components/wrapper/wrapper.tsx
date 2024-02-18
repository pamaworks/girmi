import { ReactNode, useEffect, useMemo, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Box, CircularProgress, Container, CssBaseline, Fade, ThemeProvider, createTheme } from '@mui/material';
import { useLocation } from 'react-router-dom';
import Footer from './footer';
import Header from './header';
import SignIn from '../sign/signIn';
import { RootState } from '../../modules';
import { userInfoAsync } from '../../modules/sign';
import { User } from '../../models/sign';

function Wrapper(props: { child: ReactNode }) {
	const { child } = props;
	const defaultTheme = createTheme();
	const { pathname } = useLocation();
	const [isLoading, setIsLoading] = useState(false);
	const [isLogin, setIsLogin] = useState(true);

	function checkView() {
		return <>{!isLogin ? <SignIn /> : <>{child}</>}</>;
	}

	const { data } = useSelector((state: RootState) => state.userInfo.userInfo);
	const dispatch = useDispatch();

	const userInfo = useMemo(() => {
		return data;
	}, [data]);

	useEffect(() => {
		dispatch(userInfoAsync.request(null));
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

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
				<Header user={data as User} />
				<Container maxWidth="lg">{showView()}</Container>
				<Footer />
			</ThemeProvider>
		</>
	);
}
export default Wrapper;
