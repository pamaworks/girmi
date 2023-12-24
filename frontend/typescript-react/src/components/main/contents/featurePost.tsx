import { Card, CardActionArea, CardContent, CardMedia, Grid, Typography } from "@mui/material";

function FeaturedPost() {

    const featuredPosts = [
        {
          title: 'Featured post',
          date: 'Nov 12',
          description:
            'This is a wider card with supporting text below as a natural lead-in to additional content.',
          image: 'https://source.unsplash.com/random?wallpapers',
          imageLabel: 'Image Text',
        },
        {
          title: 'Post title',
          date: 'Nov 11',
          description:
            'This is a wider card with supporting text below as a natural lead-in to additional content.',
          image: 'https://source.unsplash.com/random?wallpapers',
          imageLabel: 'Image Text',
        },
      ];

    return (
        <>
        {featuredPosts.map((post => (
            <Grid item xs={12} md={6} key={post.title}>
            <CardActionArea component="a" href="#">
              <Card sx={{ display: 'flex' }}>
                <CardContent sx={{ flex: 1 }}>
                  <Typography component="h2" variant="h5">
                    {post.title}
                  </Typography>
                  <Typography variant="subtitle1" color="text.secondary">
                    {post.date}
                  </Typography>
                  <Typography variant="subtitle1" paragraph>
                    {post.description}
                  </Typography>
                  <Typography variant="subtitle1" color="primary">
                    Continue reading...
                  </Typography>
                </CardContent>
                <CardMedia
                  component="img"
                  sx={{ width: 160, display: { xs: 'none', sm: 'block' } }}
                  image={post.image}
                  alt={post.imageLabel}
                />
              </Card>
            </CardActionArea>
            </Grid>
        )))}
        </>
    )
}
export default FeaturedPost