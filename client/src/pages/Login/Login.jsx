import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { postFetch } from '../../util/API.js';
import Button from '../../components/UI/Button.jsx';
import styles from './Login.module.css';
// import icon from ''

function Login() {
  const navigate = useNavigate();
  const [inputs, setInputs] = useState({
    username: '',
    password: ''
  });
  const { username, password } = inputs;

  const onChange = (e) => {
    const { name, value } = e.target;
    setInputs({ ...inputs, [name]: value });
  };
  console.log(inputs);

  const LOGIN_POST_URL = `${process.env.REACT_APP_URL}/login`;
  console.log(LOGIN_POST_URL);

  // ! 유효성 검사 추가
  const onSubmit = async (e) => {
    e.preventDefault();
    // ! input 값에 빈 문자열 들어올 때 사용자에게 알림 처리해줘야 함
    if (username === '' || password === '') return;

    const res = await postFetch(LOGIN_POST_URL, inputs);

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
        <form>
          <div className={styles.loginGoolglebtn}>
            <Button
              text='Log in with Google'
              addStyle={{
                borderColor: 'var(--black-750)',
                backgroundColor: 'var(--white)',
                color: 'var(--black)',
                padding: '10.4px',
                width: '219px'
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
                width: '219px'
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

                width: '219px'
              }}
            />
          </div>
        </form>

        <div className={styles.logintextForm}>
          <div className={styles.loginBar}>
            <form onSubmit={onSubmit}>
              <label htmlFor='email' className={styles.label}>
                Email
              </label>
              <input type='email' name='username' id='email' value={username} onChange={onChange} />

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

              <div className={styles.loginbtn}>
                <Button
                  text='Log in'
                  // path='/users/login'
                  addStyle={{
                    width: '180px',
                    textColor: 'var(--white)'
                  }}
                />
              </div>
            </form>
          </div>
        </div>
        <div className={styles.under}>
          <div>
            Don’t have an account?
            <Link to={`/signup`} className={styles.contentSignup}>
              sign up
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
