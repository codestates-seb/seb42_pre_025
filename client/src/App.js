import { useEffect, createContext, useMemo } from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';
import useLocalStorage from './util/useLocalStorage.js';
import Header from './components/Header.jsx';
import QuestionList from './pages/QuestionList/QuestionList.jsx';
import QuestionDetail from './pages/QuestionDetail/QuestionDetail.jsx';
import CreateQuestion from './pages/CreateQuestion/CreateQuestion.jsx';
import EditQuestion from './pages/EditQuestion/EditQuestion.jsx';
import Home from './pages/Home/Home.jsx';
import Login from './pages/Login/Login.jsx';
import Signup from './pages/Signup/Signup.jsx';
import GlobalStyles from './GlobalStyles';
import './App.css';

export const userContext = createContext({
  setIsLoggedIn: () => {},
  isLoggedIn: false,
  setTokens: () => {},
  tokens: {
    accessToken: '',
    refreshToken: ''
  }
});

function App() {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useLocalStorage('login', false);
  const [tokens, setTokens] = useLocalStorage('jwt', {
    accessToken: '',
    refreshToken: ''
  });

  const value = useMemo(
    () => ({ isLoggedIn, setIsLoggedIn, tokens, setTokens }),
    [isLoggedIn, setIsLoggedIn, tokens, setTokens]
  );

  useEffect(() => {
    const url = new URL(window.location.href);
    const accessToken = url.searchParams.get('access_token');
    const refreshToken = url.searchParams.get('refresh_token');

    if (accessToken && refreshToken) {
      setTokens({ accessToken, refreshToken });
      setIsLoggedIn(true);
      navigate('/');
    }
  }, [isLoggedIn]);

  return (
    <>
      <GlobalStyles />
      <userContext.Provider value={value}>
        <Header setIsLoggedIn={setIsLoggedIn} setTokens={setTokens} />
        <Routes>
          <Route path='/' element={isLoggedIn ? <QuestionList /> : <Home />} />
          <Route path='/questions' element={<QuestionList />} />
          <Route path='/questions/ask' element={<CreateQuestion />} />
          <Route path='/questions/edit' element={<EditQuestion />} />
          <Route path='/users/login' element={<Login />} />
          <Route
            path='/users/signup'
            element={<Signup setIsLoggedIn={setIsLoggedIn} setTokens={setTokens} />}
          />
          <Route path='/questions/:id' element={<QuestionDetail />} />
        </Routes>
      </userContext.Provider>
    </>
  );
}

export default App;
