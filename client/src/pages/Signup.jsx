import { useState } from 'react';

import styles from './Signup.module.css';
// import icon from ''
import AboutSignup from './AboutSignup.jsx';
import Button from '../components/UI/Button.jsx';
// import { getData } from '../hooks/API/API';

function Signup() {
  const [isSignup] = useState(true);

  return (
    <section className={styles.signupContainer}>
      {isSignup && <AboutSignup />}
      <div className={styles.signupContent}>
        {/* { !isSignup && <img src = {icon} alt = 'stack overflow' className='loginogo'/>} */}

        <from>
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

          {/* <button type='submit' className={styles.authBtn}>
            Sign up with GiHub
          </button> */}

          {isSignup && (
            <label htmlFor='name'>
              <h4>Display Name</h4>
              <input type='text' id='name' name='name' />
            </label>
          )}
          <label htmlFor='email'>
            <h4>Email</h4>
            <input type='email' name='email' id='email' />
          </label>
          <label htmlFor='password'>
            <h4>Password</h4>
            <input type='password' name='password' id='password' />
            {isSignup && (
              <p>
                Passwords must contain at least eight
                <br />
                characters, including at least 1<br /> letter and 1 number.
              </p>
            )}
          </label>
          <div className={styles.checkBox}>
            {isSignup && (
              <label htmlFor='check'>
                <div>
                  {/* 현  div는 새로로 쌓임, 부모태그인 라벨은 클래스가 없으므로 그위에 조상태그인 클래스인 .checkBox를 css에서 label로 불러주어 디스플레이 플렉스를 써서 가로로 플어지게 된것 */}
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
            )}
          </div>

          <div className={styles.signupbtn}>
            <Button
              text='signup'
              path='/users/signup'
              addStyle={{
                width: '219.38px',
                padding: '10.4px',
                margin: '4px',
                bgColor: 'var(--powder-100)',
                bdColor: 'var(--powder-500)',
                color: 'var(--powder-700)',

                textColor: 'var--white:)'

                // onClick={path ? () => goTo(path) : handleClick}
              }}
            />
          </div>

          {isSignup && (
            <p>
              By clicking “Sign up”, you agree to our terms of
              <br />
              service, privacy policy and cookie policy
            </p>
          )}
        </from>
      </div>
    </section>
  );
}

export default Signup;
