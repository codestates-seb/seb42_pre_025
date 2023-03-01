// import { useState, useContext } from 'react';
import { Link } from 'react-router-dom';
// import { userContext } from '../../App.js';
// import { postFetch } from '../../util/API.js';
// import { checkPassword } from '../../util/checkPassword.js';
import useLoginLogic from '../../util/useLoginLogic.js';
import Button from '../../components/UI/Button.jsx';
import styles from './Login.module.css';
// import icon from ''

function Login() {
  const initialInputs = {
    username: '',
    password: ''
  };
  const LOGIN_POST_URL = `${process.env.REACT_APP_URL}/login`;
  const msg = 'User name and password cannot be empty.';

  const [inputs, onChange, onSubmit] = useLoginLogic(
    initialInputs,
    LOGIN_POST_URL,
    msg,
    'username',
    'password'
  );

  const { username, password } = inputs;
  console.log(inputs);
  console.log(username);
  console.log(password);

  // * 여기 아래부터 원본
  // const navigate = useNavigate();
  // const [inputs, setInputs] = useState({
  //   username: '',
  //   password: ''
  // });
  // // ! inputs 리턴 받아온 다음 구조분해 할당 필요
  // const { username, password } = inputs;

  // * useContext 관련 코드
  // const { isLoggedIn, tokens } = useContext(userContext);
  // console.log('로그인 여부: ', isLoggedIn);
  // console.log(tokens);

  // const onChange = (e) => {
  //   const { name, value } = e.target;
  //   setInputs({ ...inputs, [name]: value });
  // };
  // console.log(inputs);

  // const LOGIN_POST_URL = `${process.env.REACT_APP_URL}/login`;
  // // console.log(LOGIN_POST_URL);

  // const onSubmit = async (e) => {
  //   e.preventDefault();
  //   if (username === '' || password === '') {
  //     alert('User name and password cannot be empty.');
  //     return;
  //   }

  //   const result = checkPassword(inputs.password);
  //   if (!result) return;

  //   const res = await postFetch(LOGIN_POST_URL, inputs);
  //   const accessToken = res.headers.get('Authorization');
  //   const refreshToken = res.headers.get('Refresh');

  //   if (res.ok) {
  //     setTokens({
  //       accessToken,
  //       refreshToken
  //     });
  //     setIsLoggedIn(true);
  //     navigate('/questions');
  //   }
  // };
  // console.log(tokens);

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
        </form>

        <div className={styles.logintextForm}>
          <div className={styles.loginBar}>
            <form onSubmit={onSubmit}>
              <label htmlFor='email' className={styles.label}>
                Username
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
