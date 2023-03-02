import { useContext } from 'react';
import { userContext } from '../App.js';

function useAccessToken() {
  const { tokens } = useContext(userContext);
  const accessToken = tokens && tokens.accessToken;
  return accessToken;
}

export default useAccessToken;
