import { useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { userContext } from '../App';

function useCheckLogin() {
  const navigate = useNavigate();
  const { isLoggedIn } = useContext(userContext);

  useEffect(() => {
    if (!isLoggedIn) {
      navigate('/users/login');
    }
  }, []);

  return isLoggedIn;
}

export default useCheckLogin;
