import styles from './Header.module.css';

function Header() {
  return (
    <header id={styles.header}>
      <p>HEADER</p>
      <button>Questions</button>
      <button>Log in</button>
      <button>Sign up</button>
    </header>
  );
}

export default Header;
