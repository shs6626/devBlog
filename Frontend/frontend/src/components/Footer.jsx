import "./Footer.css"
import styled from "styled-components";
const Footer = ({footerItem}) => {

    const SimpleButton = styled.button`
        color: white;
        background-color: skyblue;
    `;

    const LargeButton = styled(SimpleButton)`
        font-size: 50px;
    `;

     const PrimaryButton = styled.button`
        color: ${props => props.primary ? 'white' : 'red'};
    `;

    const asd = (a) => {

        if (a.primary) {
            return 'white';
        } else {
            return 'black';
        }

    }

    const PrimaryButton2 = styled.button`
            //color: ${(a) => (a.primary ? "white" : 'blue')}
        color: ${asd};
        
    `;

    const arsenal = () => {
        console.log(footerItem)
    }

    return (
        <div className="Footer">
            footer
            <button onClick={arsenal}>click</button>
            <SimpleButton>simple</SimpleButton>
            <LargeButton>simple</LargeButton>
            <PrimaryButton primary>P</PrimaryButton>
            <PrimaryButton >P</PrimaryButton>
            <PrimaryButton2 primary >P</PrimaryButton2>
            <PrimaryButton2  >opop</PrimaryButton2>
        </div>
    )
}

export default Footer;