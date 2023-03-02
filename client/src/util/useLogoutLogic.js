import { useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { userContext } from '../App';
import { postFetch } from './API';

async function useLogoutLogic() {
  console.log('useLogout hook 로그아웃 클릭');
  const navigate = useNavigate();
  const { setTokens, tokens, setIsLoggedIn, isLoggedIn } = useContext(userContext);

  useEffect(() => {
    localStorage.removeItem('login');
    localStorage.removeItem('jwt');
  }, [tokens, isLoggedIn]);

  const LOGOUT_POST_URL = `${process.env.REACT_APP_URL}/logout`;
  const res = await postFetch(LOGOUT_POST_URL);

  if (res) {
    setIsLoggedIn(false);
    setTokens({
      accessToken: '',
      refreshToken: ''
    });

    navigate('/');
  }
  return isLoggedIn;
}

export default useLogoutLogic;
