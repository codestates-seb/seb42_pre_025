import { Link } from 'react-router-dom';
import Button from './UI/Button.jsx';
import styles from './Header.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

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
