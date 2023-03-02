import useLoginLogic from '../../util/useLoginLogic.js';
import Button from '../../components/UI/Button.jsx';
import AboutSignup from './AboutSignup.jsx';
import styles from './Signup.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';

function Signup() {
  const initialInputs = {
    email: '',
    password: '',
    displayName: ''
  };
  const SIGNUP_POST_URL = `${process.env.REACT_APP_URL}/users`;
  const msg = 'Email, password and user name cannot be empty.';

  const [inputs, onChange, onSubmit] = useLoginLogic(
    initialInputs,
    SIGNUP_POST_URL,
    msg,
    'email',
    'password',
    'displayName'
  );

  const { email, password, displayName } = inputs;
  console.log(inputs);
  // console.log(username);
  // console.log(password);

  // * oauth - google
  const handleRequestSignupGoogle = () => {
    console.log('구글 회원가입 요청');
    return window.location.assign('https://dev.qushe8r.shop/oauth2/authorization/google');
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
        <form>
          <FontAwesomeIcon icon={faGoogle} className={styles.highlight} />

          <div className={styles.loginGoolglebtn}>
            <Button
              text='Sign up with Google'
              addStyle={{
                borderColor: 'var(--black-750)',
                backgroundColor: 'var(--white)',
                color: 'var(--black)',
                padding: '10.4px',
                width: '219px'
              }}
              handleClick={handleRequestSignupGoogle}
            />
          </div>

          <div className={styles.signupGiHubbtn}>
            <Button
              text='Sign up with GitHub'
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
              text='Sign up with Facebook'
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

        <div className={styles.signuptextFrom}>
          <div className={styles.fromContainer}>
            <div className={styles.signupBar}>
              <form onSubmit={onSubmit}>
                <label htmlFor='name' className={styles.label}>
                  Display Name
                </label>
                <input
                  type='text'
                  id='name'
                  name='displayName'
                  maxLength={16}
                  value={displayName}
                  onChange={onChange}
                />

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
                <p className={styles.desc}>
                  Passwords must contain at least eight characters, including at least 1 letter and
                  1 number.
                </p>

                <div className={styles.signupbtn}>
                  <Button
                    text='Sign up'
                    addStyle={{
                      width: '190px'
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

export default Signup;
