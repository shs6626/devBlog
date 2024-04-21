import React, {useEffect, useState} from 'react';
import axios, {post} from 'axios';

function App() {

    const [data, setData] = useState('asd');

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/post')
            .then(response => {
                console.log(response);
                let postList = response['data']['info']['postDtoPage']['content'];
                console.log('postList', postList)

                console.log(postList.map((res) => res.content));
                const aa = postList.map((res) => res.content);
                setData(aa);

            }


    )
    .catch(error => console.log(error))
    }, []);



    return (
        <div>
            {data}
            백엔드에서 가져온 데이터입니다 :
        </div>
    );
}

export default App;
