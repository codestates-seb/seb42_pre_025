import { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { userContext } from '../App';
import { postFetch } from './API';
import { checkPassword } from './checkPassword';

function useLoginLogic(initialInputs, url, alertMsg, key1, key2, key3) {
  const navigate = useNavigate();
  const [inputs, setInputs] = useState(initialInputs);
  const { setIsLoggedIn, setTokens } = useContext(userContext);

  const onChange = (e) => {
    const { name, value } = e.target;
    setInputs({ ...inputs, [name]: value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();

    if (inputs[key1] === '' || inputs[key2] === '' || inputs[key3] === '') {
      alert(alertMsg);
      return;
    }

    const result = checkPassword(inputs.password);
    if (!result) return;

    const res = await postFetch(url, inputs);
    const accessToken = res.headers.get('Authorization');
    const refreshToken = res.headers.get('Refresh');

    if (res.ok) {
      setTokens({
        accessToken,
        refreshToken
      });
      setIsLoggedIn(true);
      navigate('/questions');
    }
  };

  return [inputs, onChange, onSubmit];
}

export default useLoginLogic;
