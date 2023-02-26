import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header.jsx';
import QuestionList from './pages/QuestionList.jsx';
import CreateQuestion from './pages/CreateQuestion.jsx';
import Home from './pages/Home.jsx';
import Login from './pages/Login.jsx';
import Signup from './pages/Signup.jsx';
import QuestionDetail from './pages/QuestionDetail.jsx';
import GlobalStyles from './GlobalStyles';
import './App.css';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(true);
  console.log(setIsLoggedIn);

  return (
    <>
      <GlobalStyles />
      <Header />
      <Routes>
        <Route path='/' element={isLoggedIn ? <QuestionList /> : <Home />} />
        <Route path='/questions' element={<QuestionList />} />
        <Route path='/questions/ask' element={<CreateQuestion />} />
        <Route path='/users/login' element={<Login />} />
        <Route path='/users/signup' element={<Signup />} />
        <Route path='/questions/:id' element={<QuestionDetail />} />
      </Routes>
    </>
  );
}

export default App;
