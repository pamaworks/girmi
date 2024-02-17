import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Main from './main/main';
import SignIn from './sign/signIn';
import SignUp from './sign/signUp';
import Wrapper from './wrapper/wrapper';
import MainPost from './main/contents/mainPost';

export const noShowHeaderUrls = [] as string[];

function MainIndex() {
	return (
		<BrowserRouter basename="/">
			<Wrapper
				child={
					<Routes>
						<Route path="/" element={<Main />} />
						<Route path="/signin" element={<SignIn />} />
						<Route path="/signup" element={<SignUp />} />
						<Route path="/posts" element={<MainPost />} />
					</Routes>
				}
			/>
		</BrowserRouter>
	);
}

export default MainIndex;
