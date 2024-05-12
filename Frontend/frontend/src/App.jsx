import './App.css'
import {Route, Routes, Link, useNavigate } from "react-router-dom";
import Editor from "./components/Editor.jsx";
import List from "./components/List.jsx";
import axios from "axios";
import {useEffect, useState, createContext} from "react";
import FooterPage from "./components/FooterPage.jsx";
import NotFound from "./components/NotFound.jsx";
import Home from "./pages/Home.jsx";
import Diary from "./pages/Diary.jsx";
import New from "./pages/New.jsx";



function App() {

    const [footerItem, setFooterItem] = useState([]);
    const nav = useNavigate();

    const navHome = () => {
        nav("/")
    }

    const navNew = () => {
        nav("/new")
    }

    //1. "/" 모든 일기를 조회하는 페이지
    //2. "/new" 새로운 일기를 작성하는 new 페이지
    //3. "/diary" 일기를 상세히 조회하는 페이지

    return (
        <>
            {/*<div>얘는 고정</div>*/}
            {/*<div>*/}
            {/*    <Link to={"/new"}>New</Link>*/}
            {/*</div>*/}
            <button onClick={navHome}>Home</button>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/new" element={<New/>}/>
                <Route path="/diary/:postId" element={<Diary/>}/>
                <Route path="*" element={<NotFound/>} />
            </Routes>
        </>



    );
}

export default App;
