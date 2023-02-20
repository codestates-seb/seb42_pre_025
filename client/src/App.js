import { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';

const BASE_URL = 'https://c691-211-184-28-120.jp.ngrok.io/hello';

function App() {
  const [data, setData] = useState('');

  useEffect(() => {
    async function getData() {
      try {
        const res = await axios.get(BASE_URL);
        console.log(res.data);
        setData(res.data);
      } catch (error) {
        console.error(error);
      }
    }
    getData();
  }, []);
  console.log(data);

  return (
    <>
      <p>여기 아래에 서버에서 보내준 데이터가 뜬다면 성공입니다 :)</p>
      <p>{data}</p>
    </>
  );
}

export default App;
