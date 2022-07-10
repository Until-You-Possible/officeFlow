import React from "react";
import loginStyle from "../../css/login.module.css";
import {
    Avatar,
    Box,
    Button,
    Card,
    CardActions,
    CardContent,
    CardHeader,
    Divider, FormControl, Grid, Input, InputAdornment, InputLabel, Paper, styled, TextField,
    Typography
} from "@mui/material";

function MarginBar() {
    return (
        <Box
            sx={{
                height: 30,
                backgroundColor: (theme: any) =>
                    theme.palette.mode === 'light'
                        ? 'rgba(255, 255, 255, 1)'
                        : 'rgb(255 132 132 / 25%)',
            }}
        />
    );
}

function Choice() {
    return (
        <Box
            sx={{
                height: 50,
                textAlign: 'center',
                lineHeight: "50px",
                color: (theme: any) =>
                    theme.palette.mode === 'light'
                        ? 'rgba(0, 0, 0, .4)'
                        : 'rgb(255 132 132 / 25%)',
            }}
        >OR</Box>
    );
}



const Login: React.FC = () => {

    const handleChang = () => {

    }

    return (
        <>
            <div className={loginStyle.container}>
             <Card className={loginStyle.MRoot}>
                <CardContent>
                    <Typography className={loginStyle.MTypographyTitle}
                                color="text.secondary"
                                gutterBottom >
                        log in to your account
                    </Typography>
                    <MarginBar />
                    <TextField
                        label="username"
                        fullWidth
                        required
                        id="outlined-name"
                        color="secondary"
                        value=""
                        placeholder="please input your username"
                    />
                    <MarginBar />
                    <TextField
                        label="password"
                        fullWidth
                        required
                        color="secondary"
                        id="outlined-name"
                        value=""
                        placeholder="please input your password"
                    />
                    <MarginBar />
                    <Button fullWidth variant="contained" size="large">Log in</Button>
                    <Choice />
                    <Button fullWidth variant="outlined" size="large">Log in with wechat</Button>

                </CardContent>
            </Card>


            </div>
        </>
    )
}

export default Login;