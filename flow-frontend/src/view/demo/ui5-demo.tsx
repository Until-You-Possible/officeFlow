// ui5 控件的一些使用方法

import React from "react";
import { Button, StepInput } from "@ui5/webcomponents-react";

const UI5Demo: React.FC = () => {
    return (
        <>
            <Button className="defaultSize" design="Negative" icon="delete">Delete</Button>
            <Button design="Emphasized">Emphasized</Button>
            <Button icon="download" icon-end>Download</Button>
            <StepInput />
        </>
    )
}

export default UI5Demo;