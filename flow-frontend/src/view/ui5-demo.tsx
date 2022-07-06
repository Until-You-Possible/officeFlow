// ui5 控件的一些使用方法

import React, { Fragment } from "react";
import { Button, StepInput } from "@ui5/webcomponents-react";

function UI5Demo () {
    return <Fragment>
        <Button className="defaultSize" design="Negative" icon="delete">Delete</Button>
        <Button design="Emphasized">Emphasized</Button>
        <Button icon="download" icon-end>Download</Button>
        <StepInput />
    </Fragment>
}

export default UI5Demo;