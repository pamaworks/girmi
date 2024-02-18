import { Button, IconButton, Link, Toolbar, Typography } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { noShowHeaderUrls } from '../routers';
import { User } from '../../models/sign';

export default function Header(props: { user: User }) {
	const { user } = props;

	const sections = [
		{ title: 'Posts', url: '/posts' },
		{ title: 'Design', url: '#' },
		{ title: 'Culture', url: '#' },
		{ title: 'Business', url: '#' },
		{ title: 'Politics', url: '#' },
		{ title: 'Opinion', url: '#' },
		{ title: 'Science', url: '#' },
		{ title: 'Health', url: '#' },
		{ title: 'Style', url: '#' },
		{ title: 'Travel', url: '#' },
	];

	const navigate = useNavigate();
	const { pathname } = useLocation();

	const isNoShowHeader = () => {
		return noShowHeaderUrls.includes(pathname);
	};

	return (
		<>
			<Toolbar sx={{ borderBottom: 1, borderColor: 'divider' }}>
				<Typography component="h2" variant="h5" color="inherit" align="center" noWrap sx={{ flex: 1 }}>
					<Link href="/">GIRMI</Link>
				</Typography>
				<IconButton>
					<SearchIcon />
				</IconButton>
				{user ? (
					`Hello!!! ${user.userNm}`
				) : (
					<Button variant="outlined" onClick={() => navigate('/signin')} size="small">
						Sign In
					</Button>
				)}
			</Toolbar>
			<Toolbar component="nav" variant="dense" sx={{ justifyContent: 'space-between', overflowX: 'auto' }}>
				{sections.map((section) => (
					<Link
						color="inherit"
						noWrap
						key={section.title}
						variant="body2"
						href={section.url}
						sx={{ p: 1, flexShrink: 0 }}
					>
						{section.title}
					</Link>
				))}
			</Toolbar>
		</>
	);
}
