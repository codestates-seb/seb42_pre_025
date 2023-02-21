import { useState } from 'react';
// import API_TEST from './API_TEST.jsx';
import Header from './components/Header.jsx';
import Nav from './components/Nav.jsx';
import Footer from './components/Footer.jsx';
import QuestionList from './pages/QuestionList.jsx';
import Home from './pages/Home.jsx';
import GlobalStyles from './GlobalStyles';
import './App.css';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  console.log(setIsLoggedIn);

  return (
    <>
      <GlobalStyles />
      <Header />
      {isLoggedIn ? (
        <div className='main-container'>
          <Nav />
          <QuestionList />
        </div>
      ) : (
        <Home />
      )}
      <Footer />
    </>
  );
  // return <API_TEST />;
}

export default App;
