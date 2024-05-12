import "./Header.css"
import styled from "styled-components";

const Flex = styled.div`
    display: flex;
    justify-content: center;
    background-color: ${(props) => props.boxColor ? props.boxColor : "green"};
    border-radius: 90px;
    color: white;
    
`
const Header = () => {

    return (
        <>
            <Flex>
                <h1>Dev Blog</h1>
                {/*<h3>{new Date().toDateString()}</h3>*/}
            </Flex>
        </>

    );
}
export default Header;