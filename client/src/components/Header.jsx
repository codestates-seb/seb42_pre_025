import { Link } from 'react-router-dom';
import { postFetch } from '../util/API.js';
import Button from './UI/Button.jsx';
import styles from './Header.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

function Header({ setIsLoggedIn, setTokens }) {
  const handleLogout = async () => {
    console.log('로그아웃 클릭');
    const LOGOUT_POST_URL = `${process.env.REACT_APP_URL}/logout`;
    const res = await postFetch(LOGOUT_POST_URL);
    console.log('status code: ', res.status);
    // console.log(typeof res.status);

    if (res) {
      setIsLoggedIn(false);
      setTokens({
        accessToken: '',
        refreshToken: ''
      });
      console.log('/로 자동 이동');
      // navigate('/');
    }
  };

  return (
    <header id={styles.header}>
      <div className={styles.container}>
        <Link to='/' className={styles.logo}>
          <img
            src='https://upload.wikimedia.org/wikipedia/commons/0/02/Stack_Overflow_logo.svg'
            alt='logo'
            className={styles.logoImg}
          ></img>
        </Link>
        <ol className={styles.navigation}>
          <li>
            <Link to='/questions'>Products</Link>
          </li>
        </ol>
        <form className={styles.searchBar}>
          <FontAwesomeIcon icon={faMagnifyingGlass} className={styles.searchIco} />
          <input placeholder='Search...'></input>
        </form>
        <ol className={styles.menubar}>
          <li>
            <Button
              text='Log in'
              path='/users/login'
              addStyle={{
                borderColor: 'var(--powder-500)',
                backgroundColor: 'var(--powder-100)',
                color: 'var(--powder-700)'
              }}
            />
          </li>
          <li>
            <Button
              text='Log out'
              handleClick={handleLogout}
              addStyle={{
                borderColor: 'var(--powder-500)',
                backgroundColor: 'var(--powder-100)',
                color: 'var(--powder-700)'
              }}
            />
          </li>
          <li>
            <Button
              text='Sign up'
              path='/users/signup'
              addStyle={{
                borderColor: 'var(--blue-500)'
              }}
            />
          </li>
        </ol>
      </div>
    </header>
  );
}

export default Header;
