import React from 'react';
import { CookiesProvider } from 'react-cookie';
import Routers from './components/routers';

function App() {
	return (
		<CookiesProvider>
			<Routers />
		</CookiesProvider>
	);
}

export default App;
