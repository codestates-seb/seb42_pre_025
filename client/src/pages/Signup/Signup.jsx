import { useState } from 'react';
// useContext import 해야함
import { useNavigate } from 'react-router-dom';
// import { userContext } from '../../App';
import { postFetch } from '../../util/API';
import { checkPassword } from '../../util/checkPassword';
import Button from '../../components/UI/Button.jsx';
import AboutSignup from './AboutSignup.jsx';
import styles from './Signup.module.css';
// import icon from ''

function Signup() {
  const navigate = useNavigate();
  const [inputs, setInputs] = useState({
    email: '',
    password: '',
    displayName: ''
  });
  const { email, password, displayName } = inputs;

  // * useContext 관련 코드
  // const { setIsLoggedIn, isLoggedIn, setTokens, tokens } = useContext(userContext);
  // console.log('로그인 여부: ', isLoggedIn);
  // console.log(setIsLoggedIn);
  // console.log(setTokens);
  // console.log(tokens);

  // setTokens({
  //   accessToken: 'xasdf',
  //   refreshToken: 'test'
  // });
  // console.log(tokens);

  const onChange = (e) => {
    const { name, value } = e.target;
    setInputs({ ...inputs, [name]: value });
  };
  // console.log(inputs);

  const SIGNUP_POST_URL = `${process.env.REACT_APP_URL}/users`;

  const onSubmit = async (e) => {
    e.preventDefault();
    if (email === '' || password === '' || displayName === '') {
      alert('Email, password and user name cannot be empty.');
      return;
    }

    const result = checkPassword(inputs.password);
    if (!result) return;

    const res = await postFetch(SIGNUP_POST_URL, inputs);
    // const accessToken = res.headers.get('Authorization');
    // const refreshToken = res.headers.get('Refresh');

    if (res.ok) {
      navigate('/questions');
    }
  };

  // * oauth - google
  const handleRequestSignupGoogle = () => {
    console.log('구글 회원가입 요청');
    return window.location.assign('https://dev.qushe8r.shop/oauth2/authorization/google');
  };

  return (
    <section className={styles.signupContainer}>
      <AboutSignup />
      <div className={styles.signupContent}>
        {/* 소셜 로그인 버튼 */}
        <div>
          <div>
            <Button
              text='Sign up with Google'
              addStyle={{
                bdColor: 'var(--black-750:)',
                bgColor: 'var(--black-750:)',
                color: 'var(--powder-050:)',

                textColor: 'var--white:)',
                padding: '10.4px',
                margin: '2px 0',
                width: '219.38px'
              }}
              handleClick={handleRequestSignupGoogle}
            />
          </div>
          <Button
            text='Sign up with GiHub'
            addStyle={{
              borderColor: 'var(--powder-500)',
              backgroundColor: 'var(--powder-100)',
              color: 'var(--powder-700)',

              // borderColor: 'var(--black-750:)',
              // backgroundColor: 'var(--black-750)',
              // color: 'var(--powder-050:)',

              textColor: 'var--white:)',
              padding: '10.4px',
              margin: '4px',
              width: '219.38px'
            }}
          />

          <div>
            <Button
              text='Sign up with Facebook'
              addStyle={{
                // borderColor: 'var(--black-750:)',
                backgroundColor: 'var(--black-750)',
                // color: 'var(--powder-050:)',

                textColor: 'var(--white:)',
                padding: '10.4px',
                margin: '4px',
                width: '219.38px'
              }}
            />
          </div>
        </div>

        {/* <button type='submit' className={styles.authBtn}>
            Sign up with GiHub
          </button> */}

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
            Passwords must contain at least eight
            <br />
            characters, including at least 1<br /> letter and 1 number.
          </p>
          {/* <div className={styles.checkBox}>
            <label htmlFor='check'>
              <div>
                // 현 div는 새로로 쌓임, 부모태그인 라벨은 클래스가 없으므로 그위에 조상태그인
                클래스인 .checkBox를 css에서 label로 불러주어 디스플레이 플렉스를 써서 가로로
                플어지게 된것
                <input type='checkbox' id='check' />
              </div>
              <div>
                <p>
                  Opt-in to receive occasional product
                  <br /> updates, user research invitations,
                  <br /> company announcements, and digests.
                </p>
              </div>
            </label>
          </div> */}

          <div className={styles.signupbtn}>
            <Button
              text='Sign up'
              // path='/users/signup'
              addStyle={{
                width: '219.38px',
                padding: '10.4px',
                margin: '4px'
                // bgColor: 'var(--powder-100)',
                // bdColor: 'var(--powder-500)',
                // color: 'var(--powder-700)',

                // textColor: 'var--white:)'

                // onClick={path ? () => goTo(path) : handleClick}
              }}
            />
          </div>

          <p>
            By clicking “Sign up”, you agree to our terms of
            <br />
            service, privacy policy and cookie policy
          </p>
        </form>
      </div>
    </section>
  );
}

export default Signup;
