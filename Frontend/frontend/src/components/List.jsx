import "./List.css";
import {useParams} from "react-router-dom";
import ListItem from "./ListItems.jsx";
import {useEffect, useState} from "react";
import axios from "axios";
import styled from "styled-components";
import Modal from 'react-modal';
import NewModal from "./NewModal.jsx";


const List = () => {

    const [isOpen, setIsOpen] = useState(false);


    const openModal = () => {
        setIsOpen(true)
    }

    const closeModal = () => {
        setIsOpen(false)
    }


    return (
        <div className="List">
            <h4>게시글</h4>
            <input placeholder="검색어"/>
            <NewDiary>
                <Button onClick={openModal}> New</Button>
                <Modal isOpen={isOpen} onRequestClose={closeModal} ariaHideApp={false}>
                    <NewModal closeModal={closeModal}/>
                </Modal>
            </NewDiary>
            <ListItem/>
        </div>
    );
}


const NewDiary = styled.div`
    cursor: pointer;
    margin-bottom: 10px;
    background-color: #f0f0f0;
    padding: 10px;
    border-radius: 5px;
    display: flex;
    justify-content: end;

    &:hover {
        background-color: #e0e0e0;
    }
`;

const Button = styled.button`

    background-color: #4CAF50; /* 초록색 배경 */
    border: none;
    color: white; /* 흰색 텍스트 */
    padding: 10px 20px; /* 내부 여백 */
    text-align: center; /* 텍스트 가운데 정렬 */
    text-decoration: none; /* 밑줄 제거 */
    display: inline-block;
    font-size: 16px; /* 폰트 크기 */
    transition-duration: 0.4s; /* 변화에 걸리는 시간 */
    cursor: pointer; /* 마우스 커서 모양 */
    border-radius: 8px; /* 둥근 테두리 */

    /* 버튼에 호버 효과 적용 */

    &:hover {
        background-color: #45a049; /* 밝은 초록색 배경 */
        color: white; /* 흰색 텍스트 */
        box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2); /* 그림자 효과 */
    }

`

export default List;