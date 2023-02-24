import { useEffect, useState } from 'react';
import { getData } from '../hooks/API/API';
import styles from './Login.module.css';

function Login() {
  const [data, setData] = useState([]);
  const { REACT_APP_URL: BASE_URL } = process.env;
  const URL = `${BASE_URL}/users?page=1&size=5&sort=userId,desc`;

  console.log(URL);

  useEffect(() => {
    getData(URL, setData);
  }, []);

  console.log(data);

  return (
    <main id={styles.main}>
      로그인 페이지
      <p>3차 도전: 버셀 환경 3개 다 체크 production, preview, deployment</p>
    </main>
  );
}

export default Login;
