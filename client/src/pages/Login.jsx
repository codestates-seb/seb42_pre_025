import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { getFetch } from '../hooks/API/API.js';
import Button from '../components/UI/Button.jsx';
import styles from './Login.module.css';

function Login() {
  const navigate = useNavigate();
  const [inputs, setInputs] = useState({
    email: '',
    password: ''
  });
  const { email, password } = inputs;

  const onChange = (e) => {
    const { name, value } = e.target;
    setInputs({ ...inputs, [name]: value });
  };
  // console.log(inputs);

  const LOGIN_GET_URL = `${process.env.REACT_APP_URL}/users`;

  // ! 유효성 검사 추가
  const onSubmit = async (e) => {
    e.preventDefault();
    // ! input 값에 빈 문자열 들어올 때 사용자에게 알림 처리해줘야 함
    if (email === '' || password === '') return;

    const res = await getFetch(LOGIN_GET_URL, inputs);

    if (res.ok) {
      navigate('/questions');
    }
  };

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
        <from>
          <div className={styles.loginGoolglebtn}>
            <Button
              text='Sign up with Google'
              addStyle={{
                borderColor: 'var(--black-750)',
                backgroundColor: 'var(--white)',
                color: 'var(--black)',
                padding: '10.4px',
                width: '288px'
              }}
            />
          </div>

          <div className={styles.loginGiHubbtn}>
            <Button
              text='Log in with GiHub'
              addStyle={{
                borderColor: 'var(--black)',
                backgroundColor: 'var(--black)',
                color: 'var(--white)',
                padding: '10.4px',
                width: '288px'
              }}
            />
          </div>

          <div className={styles.loginFacebookbtn}>
            <Button
              text='Log in with Facebook'
              addStyle={{
                borderColor: 'var(--black-750)',
                backgroundColor: 'var(--blue-900)',
                color: 'var(--white)',
                padding: '10.4px',

                width: '288px'
              }}
            />
          </div>
        </from>

        <div className={styles.logintextFrom}>
          <div className={styles.loginBar}>
            <form onSubmit={onSubmit}>
              <label htmlFor='email' className={styles.label}>
                Email
              </label>
              <input type='email' name='email' id='email' value={email} onChange={onChange} />
              <label htmlFor='password' className={styles.label}>
                Password
              </label>
              <input
                type='password'
                name='password'
                id='password'
                value={password}
                onChange={onChange}
              />
              {/* ////////// */}

              {/* <a className={flex--item}
            href="/users/account-recovery">Forgot password?</a> */}

              <div className={styles.loginbtn}>
                <Button
                  text='login'
                  path='/users/login'
                  addStyle={{
                    width: '248px',
                    textColor: 'var(--white)'
                  }}
                />
              </div>

              <div className={styles.under}>
                <p>
                  Don’t have an account?
                  <br />
                  Are you an employer?
                </p>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
