import React from "react";
import loginStyle from "../../css/login.module.css";
import { useTheme } from '@mui/material/styles';
import { Box, Card, CardContent, CardMedia, IconButton, Typography } from "@mui/material";
import SkipPreviousIcon from '@mui/icons-material/SkipPrevious';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import SkipNextIcon from '@mui/icons-material/SkipNext';

const Login: React.FC = () => {

    const theme = useTheme();

    return (
        <>
            <div className={loginStyle.container}>

                <Card className={loginStyle.MCardWrapper}>
                    <Box sx={{ display: 'flex', flexDirection: 'column' }}>
                        <CardContent sx={{ flex: '1 0 auto' }}>
                            <Typography component="div" variant="h5">
                                Live From Space
                            </Typography>
                            <Typography variant="subtitle1" color="text.secondary" component="div">
                                Mac Miller
                            </Typography>
                        </CardContent>
                        <Box sx={{ display: 'flex', alignItems: 'center', pl: 1, pb: 1 }}>
                            <IconButton aria-label="previous">
                                {theme.direction === 'rtl' ? <SkipNextIcon /> : <SkipPreviousIcon />}
                            </IconButton>
                            <IconButton aria-label="play/pause">
                                <PlayArrowIcon sx={{ height: 38, width: 38 }} />
                            </IconButton>
                            <IconButton aria-label="next">
                                {theme.direction === 'rtl' ? <SkipPreviousIcon /> : <SkipNextIcon />}
                            </IconButton>
                        </Box>
                    </Box>
                    <CardMedia
                        component="img"
                        sx={{ width: 151 }}
                        image="https://mui.com/static/images/cards/live-from-space.jpg"
                        alt="Live from space album cover"
                    />
                </Card>

            </div>
        </>
    )
}

export default Login;