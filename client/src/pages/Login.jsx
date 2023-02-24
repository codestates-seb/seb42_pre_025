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
      <p>3차: 버셀 환경 3개 다 체크 production, preview, deployment</p>
      <p>
        4차: fetch api에 header Accept 설정 추가 - 결과: 헤더 설정 추가해도 syntax 에러 안사라짐
      </p>
      <p>5차: try catch에서 err 일단 지워보기</p>
      <p>6차: 버셀 배포 브랜치 dev에서 feat으로 변경</p>
      <p>
        7차: 버셀 환경 3개 다 체크한 것이 왜인지 풀려있고, 프로덕션만 체크 되어 있어서 다시 세 개 다
        체크
      </p>
    </main>
  );
}

export default Login;
