import React from "react";
import { Box } from "@mui/material";

interface HeightType {
    height?    : number
}
const HeightBar: React.FC<HeightType> = (prop) => {
    const height = prop.height || 20;
    return (
        <Box
            sx={{
                height: height,
                lineHeight: height + "px",
                backgroundColor: (theme: any) =>
                    theme.palette.mode === 'light'
                        ? 'rgba(255, 255, 255, 1)'
                        : 'rgb(255 132 132 / 25%)',
            }}
        />
    );
}

export default HeightBar