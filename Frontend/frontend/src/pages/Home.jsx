import {useSearchParams, useParams} from "react-router-dom";

import Header from "../components/Header.jsx";
import List from "../components/List.jsx";
import Footers from "../components/Footers.jsx";

const Home = () => {
    
    return (
        <div>
            <Header/>
            <List/>
            <Footers/>
        </div>
    );

}

export default Home;