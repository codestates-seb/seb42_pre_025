import { useNavigate } from 'react-router-dom';
import Button from './UI/Button.jsx';
import styles from './Header.module.css';

function Header() {
  const navigate = useNavigate();

  const goTo = (path) => {
    navigate(path);
  };

  return (
    <header id={styles.header}>
      <button onClick={() => goTo('/')}>스택오버플로우 로고</button>
      <button onClick={() => goTo('/questions')}>Questions</button>
      <Button
        text='Log in'
        path='/users/login'
        addStyle={{
          borderColor: 'var(--button-bd-color)',
          backgroundColor: 'var(--button-secondary-bg-color)',
          color: 'var(--button-filled-color)'
        }}
      />
      <Button text='Sign up' path='/users/signup' />
    </header>
  );
}

export default Header;
