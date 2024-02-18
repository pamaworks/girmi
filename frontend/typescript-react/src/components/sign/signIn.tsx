import { useEffect } from 'react';
import {
	Avatar,
	Box,
	Button,
	Checkbox,
	Container,
	CssBaseline,
	FormControlLabel,
	Grid,
	TextField,
	Typography,
	Link,
} from '@mui/material';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../../modules';
import { signinUserAsync } from '../../modules/sign';
import { setCookie } from '../../lib/cookie';

function SignIn() {
	const { data, loading, error } = useSelector((state: RootState) => state.signin.signin);
	const dispatch = useDispatch();

	const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
		event.preventDefault();
		const formData = new FormData(event.currentTarget);
		dispatch(signinUserAsync.request(formData));
	};

	useEffect(() => {
		if (data?.token) {
			setCookie('token-jwt', data.token, {});
			window.open('/', '_self');
		}
	}, [loading, data]);

	return (
		<Container component="main" maxWidth="xs">
			<CssBaseline />
			<Box
				sx={{
					marginTop: 8,
					display: 'flex',
					flexDirection: 'column',
					alignItems: 'center',
				}}
			>
				<Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
					<LockOutlinedIcon />
				</Avatar>
				<Typography component="h1" variant="h5">
					Sign in
				</Typography>
				<Box component="form" noValidate sx={{ mt: 1 }} onSubmit={handleSubmit}>
					<TextField
						margin="normal"
						required
						fullWidth
						id="userId"
						label="userId"
						name="userId"
						autoComplete="ID"
						autoFocus
					/>
					<TextField
						margin="normal"
						required
						fullWidth
						name="userPw"
						label="Password"
						type="password"
						id="userPw"
						autoComplete="current-password"
					/>
					<FormControlLabel control={<Checkbox value="remember" color="primary" />} label="Remember me" />
					<Button type="submit" fullWidth variant="contained" sx={{ mt: 3, mb: 2 }}>
						Sign In
					</Button>
					<Grid container>
						<Grid item xs>
							<Link href="http://localhost:8010/sign-jwt/swagger-ui.html" variant="body2">
								Forgot password?
							</Link>
						</Grid>
						<Grid item>
							<Link href="/signup" variant="body2">
								Don't have an account? Sign Up
							</Link>
						</Grid>
					</Grid>
				</Box>
			</Box>
		</Container>
	);
}
export default SignIn;
