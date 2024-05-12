import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import styled from "styled-components";
import {Button} from "../components/NewModal.jsx";
import Modal from "react-modal";

const Diary = () => {

    const params = useParams();
    const [deleteDiaryStatus, setDeleteDiaryStatus] = useState(false);

    const [detailList, setDetailList] = useState([]);
    const nav = useNavigate();

    const navHome = () => {
        nav("/")
    }


    useEffect(() => {

        console.log("Detail page ")
        axios.get("http://localhost:8080/api/v1/post")
            .then((response) => {
                setDetailList(response.data.responseData.postDtoList.filter((res) => res.postId == params.postId))
            })
    }, []);

    const arsenal = () => {
        console.log(detailList)
        console.log(params.postId)

    }

    const defaultDelete = () => {
        setDeleteDiaryStatus(true)

    }


    const deleteDiary = async () => {
        try {
            const response = await axios.delete(`http://localhost:8080/api/v1/post/${params.postId}`);

            console.log('DELETE 요청 성공:', response.data);
        } catch (error) {
            console.log('DELETE 요청 실패:', error);
        }

        navHome();
    }

    return (
        <>
            <DetailList>
                <DetailListItem size="large">
                    {params.postId}번 Diary
                </DetailListItem>
                <DetailListItem>
                    {detailList[0]?.content}
                </DetailListItem>
            </DetailList>
            <Button onClick={arsenal}>수정</Button>
            <Button onClick={deleteDiary}>삭제</Button>
            <Button onClick={defaultDelete}>삭제중</Button>
            <Modal isOpen={deleteDiaryStatus}>
                good
            </Modal>

        </>

    );
}

const DetailList = styled.div`
    padding: 0;
`
const DetailListItem = styled.div`
    cursor: pointer;
    margin-bottom: 100px;
    background-color: #f0f0f0;
    padding: 10px;
    border-radius: 5px;
    display: flex;
    justify-content: center;
    font-size: ${props => props.size || 'small'};

    &:hover {
        background-color: #e0e0e0;
    }
`


export default Diary;