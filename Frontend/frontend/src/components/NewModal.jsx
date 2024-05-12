import React, {useState} from 'react';
import styled from 'styled-components';
import axios from "axios";


const NewModal = ({closeModal}) => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const handleSubmit = async (e) => {
        // e.preventDefault(); //새로고침을 막고자할 때

        try {
            const response = await axios.post('http://localhost:8080/api/v1/post', {
                title: title,
                content: content,
                userId: 'arsenal',
                createdAt: new Date().toDateString(),
                updatedAt: '',
                deletedAt: '',
            });

            console.log('POST 요청 성공:', response.data);
        } catch (error) {
            console.error('POST 요청 실패:', error);
        }

    };


    return (
        <NewPostForm onSubmit={handleSubmit}>
            <FormGroup>
                <Label htmlFor="title">제목</Label>
                <Input
                    type="text"
                    id="title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                />
            </FormGroup>
            <FormGroup>
                <Label htmlFor="content">내용</Label>
                <TextArea
                    id="content"
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                    required
                />
            </FormGroup>
            <Flex>
                <Button type="submit">게시글 작성</Button>
                <Button onClick={closeModal}>Close</Button>
            </Flex>
        </NewPostForm>
    );
}

const Flex = styled.div`

    display: flex;
    flex-direction: row;
    justify-content: end;
    gap: 10px;

`

const NewPostForm = styled.form`
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
`;

const FormGroup = styled.div`
    margin-bottom: 20px;
`;

const Label = styled.label`
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
`;

const Input = styled.input`
    width: 90%;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
`;

const TextArea = styled.textarea`
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
`;

export const Button = styled.button`
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 4px;
    cursor: pointer;

    &:hover {
        background-color: #0056b3;
    }
`;

export default NewModal