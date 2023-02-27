import { useState, useEffect } from 'react';
import Button from '../components/UI/Button.jsx';
import { Link } from 'react-router-dom';
import styles from './Login.module.css';
// import { useState } from 'react';
// import { getData } from '../hooks/API/API.js';
import { getFetch } from '../hooks/API/API.js';

// import { useEffect, useState } from 'react';
// function Login() {

function Login() {
  const [isLogin, setIsLoggedIn] = useState(true);
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
        <div className={styles.logotop}>
          <Link to='/' className={styles.logo}>
            <img
              src='https://upload.wikimedia.org/wikipedia/commons/0/02/Stack_Overflow_logo.svg'
              alt='logo'
              className={styles.logoImg}
            ></img>
          </Link>
        </div>
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
        <from>
          <div className={styles.loginGoolglebtn}>
            <Button
              text='Sign up with Google'
              addStyle={{
                bdColor: 'var(--black-750)',
                bgColor: 'var(--white)',

                color: 'var(--black)',
                padding: '10.4px',
                margin: '20px 0',
                width: '288px'
              }}
            />
          </div>

          <div className={styles.loginGiHubbtn}>
            <Button
              text='Log in with GiHub'
              addStyle={{
                bdColor: 'var(--black)',
                bgColor: 'var(--black)',
                color: 'var(--white)',
                padding: '10.4px',
                margin: '4px 0',
                width: '288px'
              }}
            />
          </div>

          <div className={styles.loginFacebookbtn}>
            <Button
              text='Log in with Facebook'
              addStyle={{
                borderColor: 'var(--black-750)',
                backgroundColor: 'var(--black-750)',
                color: 'var(--white)',
                padding: '10.4px',

                width: '288px'
              }}
            />
          </div>
        </from>

        <div className={styles.logintextFrom}>
          <form className={styles.loginBar}>
            {isLogin && (
              <label htmlFor='email'>
                <h4>Email</h4>
                <input type='email' name='email' id='email' />
              </label>
            )}
            <label htmlFor='password'>
              <h4>Password</h4>
              <input type='password' name='password' id='password' />
            </label>
            <div className={styles.loginbtn}>
              <Button
                text='login'
                path='/users/signup'
                addStyle={{
                  bgColor: 'var(--powder-100)',
                  bdColor: 'var(--powder-500)',
                  textColor: 'var(--white)'
                }}
              />
            </div>

            {isLogin && (
              <p>
                Don’t have an account?
                <br />
                Are you an employer?
              </p>
            )}
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
