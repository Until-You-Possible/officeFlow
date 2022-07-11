import React, { useState } from "react";
import loginStyle from "../../css/login.module.css";
import {
    Avatar,
    Box,
    Button,
    Card,
    CardContent,
    Divider,
    FormControl,
    IconButton,
    InputAdornment,
    InputLabel,
    OutlinedInput,
    TextField
} from "@mui/material";

import { Visibility, VisibilityOff } from "@mui/icons-material";
import HeightBar from "../../util/HeightBar";


interface State {
    showPassword : boolean;
    username : string;
    password : string;
}

interface ConditionState {
    focusedUsername?: boolean,
    focusedPassword?: boolean
}

function ChoiceBar () {
    return (
        <Box
            sx={{
                height: 40,
                lineHeight: 40 + "px",
                textAlign: "center",
                color: (theme: any) =>
                    theme.palette.mode === 'light'
                        ? 'rgba(0, 0, 0, .5)'
                        : 'rgb(255 132 132 / 25%)',
            }}
        >or</Box>
    );
}



const Login: React.FC = () => {

    const [values, setValues] = useState<State>({
        username     : '',
        password     : '',
        showPassword : false
    });

    const [focusedValue, setFocusedValue] = useState<ConditionState>({
        focusedUsername: false,
        focusedPassword: false
    });

    const handleChange = (prop: keyof State) => (event: React.ChangeEvent<HTMLInputElement>) => {
            setValues({ ...values, [prop]: event.target.value });
        };

    const handleClickShowPassword = () => {
        setValues({
            ...values,
            showPassword: !values.showPassword,
        });
    };

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement> | undefined) => {
        event?.preventDefault();
    };

    const LoginButton = () => {
        if (!values.username) {
            setFocusedValue({
                focusedUsername: true
            });
        }
    }

    return (
        <>
            <div className={loginStyle.container}>
             <Card className={loginStyle.MRoot}>
                <CardContent>
                    <Box className={loginStyle.MAvatar}>
                        <Avatar src="https://mui.com/static/images/avatar/1.jpg" />
                    </Box>
                    <HeightBar />
                    <TextField
                        label="username"
                        fullWidth
                        required
                        focused={focusedValue.focusedUsername}
                        id="outlined-name"
                        value={values.username}
                        onChange={handleChange('username')}
                        placeholder="please input your username"
                    />
                    <HeightBar height={30} />
                    <FormControl fullWidth >
                        <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                        <OutlinedInput
                            id="outlined-adornment-password"
                            fullWidth
                            size="medium"
                            autoComplete="new-password"
                            type={values.showPassword ? 'text' : 'password'}
                            value={values.password}
                            onChange={handleChange('password')}
                            endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    edge="end"
                                    onClick={handleClickShowPassword}
                                    onMouseDown={handleMouseDownPassword}
                                >
                                    {values.showPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                        }
                            label="Password"
                        />
                    </FormControl>
                    <HeightBar />
                    <Button
                        fullWidth
                        style={{textTransform: "none"}}
                        variant="contained"
                        onClick={LoginButton}
                        size="large">Log in</Button>
                    <ChoiceBar />
                    <Button
                        style={{textTransform: "none"}}
                        fullWidth
                        variant="outlined"
                        size="large">Log in with wechat</Button>
                    <HeightBar />
                    <Divider />
                    <HeightBar height={26} />
                    <Button
                        fullWidth
                        style={{textTransform: "none"}}
                        variant="text">Can't log in ?  · Sign up for account</Button>
                </CardContent>
            </Card>


            </div>
        </>
    )
}

export default Login;