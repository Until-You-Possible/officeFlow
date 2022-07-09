import React from 'react';
import Index from "./view/demo";
import './css/App.css';
import { Routes, Route, Navigate } from "react-router-dom";
import Home from "./view/home";
import Login from "./view/login";


function App() {
  return (
          <Routes>
              <Route path="/" element={<Navigate to="/home" />} />
              <Route path="/home" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/demo" element={<Index />} />
          </Routes>
  );
}

export default App;
