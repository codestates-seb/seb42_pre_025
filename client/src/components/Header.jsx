import { useNavigate } from 'react-router-dom';
import styles from './Header.module.css';

function Header() {
  const navigate = useNavigate();

  const goTo = (path) => {
    navigate(path);
  };

  return (
    <header id={styles.header}>
      <p>HEADER</p>
      <button onClick={() => goTo('/')}>스택오버플로우 로고</button>
      <button onClick={() => goTo('/questions')}>Questions</button>
      <button onClick={() => goTo('/users/login')}>Log in</button>
      <button onClick={() => goTo('/users/signup')}>Sign up</button>
    </header>
  );
}

export default Header;
