import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import React, {useEffect, useState} from 'react';
import { Routes, Route, Link } from 'react-router-dom'
import Signup from './components/Signup';
import First from './components/First';
import Search from './components/Search';
import Detail from './components/Detail';

function App() {
  return (
    <div>
      <Routes>
        <Route path="/sign-up" element={ <Signup></Signup> } />
        <Route path="/" element={<First></First>} />
        <Route path="/search" element={<Search/>}/>
        <Route path="/detail/:exhibitionId" element={<Detail/>}/>
      </Routes>
    </div>
  );
}

export default App;
