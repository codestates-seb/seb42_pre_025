import { Link } from 'react-router-dom';
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

  // * oauth - google
  const handleRequestSignupGoogle = () => {
    console.log('구글 회원가입 요청');
    return window.location.assign('https://dev.qushe8r.shop/oauth2/authorization/google');
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
              text='Sign up with Google'
              addStyle={{
                borderColor: 'var(--black-750)',
                backgroundColor: 'var(--white)',
                color: 'var(--black)',
                padding: '10.4px',
                width: '288px'
              }}
              handleClick={handleRequestSignupGoogle}
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
