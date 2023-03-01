import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { postFetch } from '../../util/API.js';
import Button from '../../components/UI/Button.jsx';
import styles from './Signup.module.css';
import AboutSignup from './AboutSignup.jsx';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';

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
    <div className={styles.signupAuth}>
      <AboutSignup />
      {/* AboutSignup 페이지 연결 */}

      <div className={styles.signupContainer}>
        <div className={styles.logotop}></div>

        <div className={styles.authSignup}>
          <div className={styles.authSignupContainer}> </div>
        </div>
        <from>
          <FontAwesomeIcon icon={faGoogle} className={styles.highlight} />

          <div className={styles.loginGoolglebtn}>
            <Button
              text='  Sign up with Google'
              addStyle={{
                borderColor: 'var(--black-750)',
                backgroundColor: 'var(--white)',
                color: 'var(--black)',
                padding: '10.4px',
                width: '219px'
              }}
            />
          </div>

          <div className={styles.signupGiHubbtn}>
            <Button
              text='Signup in with GitHub'
              addStyle={{
                borderColor: 'var(--black)',
                backgroundColor: 'var(--black)',
                color: 'var(--white)',
                padding: '10.4px',
                width: '219px'
              }}
            />
          </div>

          <div className={styles.signupFacebookbtn}>
            <Button
              text='Signup in with Facebook'
              addStyle={{
                borderColor: 'var(--black-750)',
                backgroundColor: 'var(--blue-900)',
                color: 'var(--white)',
                padding: '10.4px',
                width: '219px'
              }}
            />
          </div>
        </from>

        <div className={styles.signuptextFrom}>
          <div className={styles.fromContainer}>
            <div className={styles.signupBar}>
              <form onSubmit={onSubmit}>
                <label htmlFor='email' className={styles.label}>
                  Display Name
                </label>
                <input
                  type='email'
                  name='username'
                  id='email'
                  value={username}
                  onChange={onChange}
                />

                <label htmlFor='email' className={styles.label}>
                  Email
                </label>
                <input type='email' name='username' id='email' value={username} />

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

                <div className={styles.signupbtn}>
                  <Button
                    text='Sign up'
                    addStyle={{
                      width: '190px',

                      textColor: 'var(--white)'
                    }}
                  />
                </div>
              </form>
            </div>
          </div>
          <div className={styles.under}>
            <div>
              By clicking “Sign up”, you agree to our terms of service, privacy policy and cookie
              policy
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
