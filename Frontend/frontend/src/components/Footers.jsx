import {useEffect, useState} from "react";
import axios from "axios";
import styled from "styled-components";

const Footers = () => {

    const [pagesNumber, setPagesNumber] = useState();

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/post")
            .then((response) => {
                setPagesNumber(response.data.responseData.pagingInfo.pageSize)
            })
    }, []);


    const getPageNumber = () => {
        axios.get("http://localhost:8080/api/v1/post")
            .then((response) => {

                console.log(response.data);

            }).catch((error) => {
            console.log(error);
        });
    }


    return (
        <>
            <FooterBox>
                {pagesNumber}
            </FooterBox>
            <button onClick={getPageNumber}>Click</button>
        </>
    );
}

const FooterBox = styled.div`
    display: flex;
    flex-direction: row;
    padding: 20px;
    justify-content: center;
    color: skyblue;

`

export default Footers;