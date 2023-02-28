import { useState, useEffect } from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';
import Header from './components/Header.jsx';
import QuestionList from './pages/QuestionList.jsx';
import CreateQuestion from './pages/CreateQuestion.jsx';
import Home from './pages/Home.jsx';
import Login from './pages/Login.jsx';
import Signup from './pages/Signup/Signup.jsx';
import QuestionDetail from './pages/QuestionDetail.jsx';
import GlobalStyles from './GlobalStyles';
import './App.css';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [tokens, setTokens] = useState({
    accessToken: '',
    refreshToken: ''
  });
  const navigate = useNavigate();

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
  console.log(tokens);
  console.log('로그인 여부', isLoggedIn);

  return (
    <>
      <GlobalStyles />
      <Header />
      <Routes>
        <Route path='/' element={isLoggedIn ? <QuestionList /> : <Home />} />
        <Route path='/questions' element={<QuestionList />} />
        <Route path='/questions/ask' element={<CreateQuestion />} />
        <Route path='/users/login' element={<Login />} />
        <Route path='/users/signup' element={<Signup setIsLoggedIn={setIsLoggedIn} />} />
        <Route path='/questions/:id' element={<QuestionDetail />} />
      </Routes>
    </>
  );
}

export default App;
