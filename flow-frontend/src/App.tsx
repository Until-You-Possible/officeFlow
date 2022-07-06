import React from 'react';
import './css/App.css';
import { ThemeProvider } from "@ui5/webcomponents-react";
import "@ui5/webcomponents-icons/dist/AllIcons.js";
import UI5Demo from "./view/ui5-demo";

function App() {
  return (
      <ThemeProvider>
        <div className="App">
            <UI5Demo />
        </div>
      </ThemeProvider>
  );
}

export default App;
