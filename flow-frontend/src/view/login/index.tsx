import React from "react";
import loginStyle from "../../css/login.module.css";
import {
    Button,
    Card,
    CardActions,
    CardContent,
    CardHeader,
    Divider, FormControl, Input, InputAdornment, InputLabel, TextField,
    Typography
} from "@mui/material";

import {AccountCircle} from "@mui/icons-material";


const Login: React.FC = () => {

    const handleChang = () => {

    }

    return (
        <>
            <div className={loginStyle.container}>

                <Card className={loginStyle.MRoot}>
                    <CardHeader title="Login" className={loginStyle.MHead} />
                    <Divider variant="middle" />
                    <CardContent>

                        <TextField
                            id="outlined-name"
                            label="Username"
                            size="small"
                            value="333"
                        />

                        <TextField
                            id="outlined-name"
                            label="Password"
                            size="small"
                            value="333"
                        />

                    </CardContent>
                    <Divider variant="middle" />
                    <CardActions className={loginStyle.MAction}>
                        <Button variant="contained" color="primary" className={loginStyle.MButton}>
                            continue
                        </Button>
                    </CardActions>
                </Card>

            </div>
        </>
    )
}

export default Login;