import { Divider, Grid, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import post1 from './post/post1.md';
import post2 from './post/post2.md';
import post3 from './post/post3.md';
import Markdown from './markdown';

function MainPost() {
	const posts = [post1, post2, post3];
	const [markdownText, setMarkdownText] = useState([] as Array<string>);

	useEffect(() => {
		const list = [] as Array<string>;
		posts.forEach((post: string, index: number) => {
			fetch(post)
				.then((res) => {
					return res.text();
				})
				.then((data) => {
					list.push(data);
					if (index === posts.length - 1) {
						setMarkdownText(list);
					}
				});
		});
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	return (
		<Grid
			item
			xs={12}
			md={8}
			sx={{
				'& .markdown': {
					py: 3,
				},
			}}
		>
			<Typography variant="h6" gutterBottom>
				From the posts
			</Typography>
			<Divider />
			{markdownText.map((post: string) => (
				<Markdown className="markdown">{post}</Markdown>
			))}
		</Grid>
	);
}
export default MainPost;
