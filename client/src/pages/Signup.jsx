import { useState } from 'react';
import styles from './Signup.module.css';
// import icon from ''
import AboutSignup from './AboutSignup.jsx';

function Signup() {
  const [isSignup] = useState(true);

  return (
    <section className={styles.signupContainer}>
      {isSignup && <AboutSignup />}
      <div className={styles.signupContainer}>
        {/* { !isSignup && <img src = {icon} alt = 'stack overflow' className='loginogo'/>} */}
        <from>
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
          {isSignup && (
            <label htmlFor='check'>
              <input type='checkbox' id='check' />
              <p>
                Opt-in to receive occasional product
                <br /> updates, user research invitations,
                <br /> company announcements, and digests.
              </p>
            </label>
          )}
          <button type='submit' className='authBtn'>
            {isSignup ? 'Sign up' : 'Log in'}
          </button>
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
