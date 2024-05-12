import styled from "styled-components";
const FooterPage = ({footerItem}) => {

    const PageNumber = styled.div`

        display: flex;
        flex-direction: row;
        gap: 10px;

    `;

    const arsenal = () => {
        console.log(footerItem)
    }


    return (
        <>
            <button onClick={arsenal}>asd</button>
            <PageNumber >여기에 페이지 수</PageNumber>
            <PageNumber>여기에 페이지 수</PageNumber>
        </>
    );
}

export default FooterPage;