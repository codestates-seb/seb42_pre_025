import { useState, useEffect } from 'react';
import Button from '../components/UI/Button.jsx';
import { Link } from 'react-router-dom';
import styles from './Login.module.css';

// import { getData } from '../hooks/API/API.js';
import { getFetch } from '../hooks/API/API.js';

// import { useEffect, useState } from 'react';
// function Login() {

function Login() {
  const [isSignup, setIsLoggedIn] = useState(true);
  console.log(setIsLoggedIn);

  const [data, setData] = useState([]);
  const { REACT_APP_URL: BASE_URL } = process.env;
  const LOGIN_GET_URL = `${BASE_URL}/users?page=1&size=5&sort=userId,desc`;

  useEffect(() => {
    getFetch(LOGIN_GET_URL, setData);
  }, []);

  console.log(data);

  return (
    <div className={styles.loginAuth}>
      <div className={styles.loginContainer}>
        <Link to='/' className={styles.logo}>
          <img
            src='https://upload.wikimedia.org/wikipedia/commons/0/02/Stack_Overflow_logo.svg'
            alt='logo'
            className={styles.logoImg}
          ></img>
        </Link>
        {/* { !isSignup && <img src = {icon} alt = 'stack overflow' className='loginogo'/>} */}

        <div className={styles.authLogin}>
          <div className={styles.authLoginContainer}> </div>
        </div>
        {/* {isSignup ? (
          <>
          <div className={styles.authLogin}></div>
          </>
        ) : ()
        }
         */}

        <div className={styles.signupbtn}>
          <p>
            <Button
              text='Log in with Google'
              addStyle={{
                bdColor: 'var(--black-750:)',
                bgColor: 'var(--white:)',
                textColor: 'var(--white:)',
                padding: '10.4px',
                margin: '4px 0',
                width: '288px'
              }}
            />
          </p>

          <p>
            <Button
              text='Log in with GiHub'
              addStyle={{
                bdColor: 'var(--black-750:)',
                bgColor: 'var(--black-750:)',
                textColor: 'var(--black-750:)',
                padding: '10.4px',
                margin: '4px 0',
                width: '288px'
              }}
            />
          </p>

          <p>
            <Button
              text='Log in with Facebook'
              addStyle={{
                bdColor: 'var(--black:)',
                bgColor: 'var(--black:)',
                textColor: 'var(--white:)',
                padding: '10.4px',
                margin: '4px 0',
                width: '288px'
              }}
            />
          </p>

          <form className={styles.loginBar}>
            {isSignup && (
              <label htmlFor='email'>
                <h4>Email</h4>
                <input type='email' name='email' id='email' />
              </label>
            )}
            <label htmlFor='password'>
              <h4>Password</h4>
              <input type='password' name='password' id='password' />
            </label>
          </form>
          <div className={styles.signupbtn}> </div>
          <Button
            text='login up'
            path='/users/signup'
            addStyle={{
              width: '288px',
              padding: '10.4px',
              margin: '4px',
              bgColor: 'var(--powder-100)',
              bdColor: 'var(--powder-500)',
              color: 'var(--powder-700)',

              textColor: 'var(--white:)'
            }}
          />
          <p>{isSignup ? 'Login' : 'isSignup'} </p>
        </div>
      </div>
    </div>
  );
}

export default Login;

// {/* // console.log(data);

//   return (
//     <main id={styles.main}>
//       로그인 페이지
//       <p>3차: 버셀 환경 3개 다 체크 production, preview, deployment</p>
//       <p>
//         4차: fetch api에 header Accept 설정 추가 - 결과: 헤더 설정 추가해도 syntax 에러 안사라짐
//       </p>
//       <p>5차: try catch에서 err 일단 지워보기</p>
//       <p>6차: 버셀 배포 브랜치 dev에서 feat으로 변경</p>
//       <p>
//         7차: 버셀 환경 3개 다 체크한 것이 왜인지 풀려있고, 프로덕션만 체크 되어 있어서 다시 세 개 다
//         체크
//       </p>
//       <p>8차: try catch 다시 되살림</p>
//       <p>9차: fetch api에 header Accept 설정 삭제</p>
//     </main>
//   );
// }

// export default Login; */}
