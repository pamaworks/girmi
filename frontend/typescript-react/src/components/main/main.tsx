import React from 'react';
import MainFeaturedPost from './contents/mainFeaturedPost';
import { Grid } from '@mui/material';
import FeaturedPost from './contents/featurePost';
import MainPost from './contents/mainPost';
import Sidebar from './contents/sidebar';

function Main() {
	return (
		<main>
			<MainFeaturedPost />
			<Grid container spacing={4}>
				<FeaturedPost />
			</Grid>
			<Grid container spacing={5} sx={{ mt: 3 }}>
				<MainPost />
				<Sidebar />
			</Grid>
		</main>
	);
}

export default Main;
