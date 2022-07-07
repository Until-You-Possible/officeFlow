import React from 'react';
import { ThemeProvider } from "@ui5/webcomponents-react";
import UI5Demo from "./view/demo/ui5-demo";
import './css/App.css';
import { Routes, Route, Navigate } from "react-router-dom";
import Home from "./view/home";
import Login from "./view/login";

function App() {
  return (
      <ThemeProvider>
              <Routes>
                  <Route path="/" element={<Navigate to="/home" />} />
                  <Route path="/home" element={<Home />} />
                  <Route path="/login" element={<Login />} />
                  <Route path="/ui5demo" element={<UI5Demo />} />
              </Routes>
      </ThemeProvider>
  );
}

export default App;
