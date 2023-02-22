import { Link } from 'react-router-dom';
import Button from './UI/Button.jsx';
import styles from './Header.module.css';

function Header() {
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
          <input placeholder='Search...'></input>
        </form>
        <ol className={styles.menubar}>
          <li>
            <Button
              text='Log in'
              path='/users/login'
              addStyle={{
                borderColor: 'var(--button-bd-color)',
                backgroundColor: 'var(--button-secondary-bg-color)',
                color: 'var(--button-filled-color)'
              }}
            />
          </li>
          <li>
            <Button text='Sign up' path='/users/signup' />
          </li>
        </ol>
      </div>
    </header>
  );
}

export default Header;
