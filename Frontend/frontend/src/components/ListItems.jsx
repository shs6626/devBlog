import "./ListItems.css";
import axios from "axios";
import styled from "styled-components";
import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";


const ListItems = () => {

    const [listItems, setListItems] = useState([]);
    const params = useParams();
    const nav = useNavigate();

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/post")
            .then((response) => {
                setListItems(response.data.responseData.postDtoList)
            })
    }, []);

    const navList = (id) => {
        console.log(id)
        nav(`/diary/${id}`)

    }

    return (
        <div>
            <PostList>
                {listItems.map((res) => (
                    <PostItem key={res.postId} onClick={() =>navList(res.postId)}>
                        <PostNumber>
                            {res.postId}
                        </PostNumber>
                        <PostTitle>
                            {res.title}
                        </PostTitle>
                        <PostDate>
                            {res.createdAt.slice(2, 9)}
                        </PostDate>
                    </PostItem>
                ))}
            </PostList>
        </div>
    );
}
const PostList = styled.div`
  list-style-type: none;
  padding: 0;
`;

const PostItem = styled.div`
    cursor: pointer;
    margin-bottom: 10px;
    background-color: #f0f0f0;
    padding: 10px;
    border-radius: 5px;
    display: flex;

    &:hover {
        background-color: #e0e0e0;
    }
`;

const PostNumber = styled.span`
  margin-right: 10px;
  font-weight: bold;
`;

const PostTitle = styled.span`
  flex: 1;
`;

const PostDate = styled.span`
  font-size: 0.8em;
`;


const StyledListTitle = styled.div`
    display: flex;
    justify-content: center;
    color: white;
    background: red;
    border: black solid;
    gap : 30px;
`;


export default ListItems;