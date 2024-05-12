import styled from "styled-components";

const SimpleButton = styled.button`
    color: skyblue;
    background-color: black;
    width: 100px;
`
const Button = ({children, ...rest}) => {


    return  (
        <>
            <SimpleButton {...rest}>{children}</SimpleButton>
        </>
    )
}

export default Button;