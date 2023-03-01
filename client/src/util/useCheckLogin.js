import { useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { userContext } from '../App';

function useCheckLogin() {
  const navigate = useNavigate();
  const { isLoggedIn } = useContext(userContext);

  // 로그인되어 있지 않으면 로그인 페이지로 리디렉션
  useEffect(() => {
    console.log('로그인 여부:', isLoggedIn);
    if (!isLoggedIn) {
      navigate('/users/login');
    }
  }, []);

  return isLoggedIn;
}

export default useCheckLogin;
