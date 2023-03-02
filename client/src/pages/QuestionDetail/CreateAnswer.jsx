import { useState, useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { userContext } from '../../App.js';
// import useCheckLogin from '../../util/useCheckLogin.js';
import { postFetch } from '../../util/API';
import Editor from '../../components/UI/Editor.jsx';
import Button from '../../components/UI/Button.jsx';
import styles from './CreateAnswer.module.css';

function CreateAnswer() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [answerContent, setAnswerContent] = useState('');

  // 로그인되어 있지 않으면 로그인 페이지로 리디렉션
  // useCheckLogin();

  // * answer POST 요청
  const ANSWER_POST_URL = `${process.env.REACT_APP_URL}/questions/${id}/add`;
  const { isLoggedIn, tokens } = useContext(userContext);
  const accessToken = tokens && tokens.accessToken;

  const onSubmit = async (e) => {
    e.preventDefault();
    // ! input 값에 빈 문자열 들어올 때 사용자에게 알림 처리해줘야 함
    if (answerContent === '') return;

    const newData = {
      // userId,
      content: answerContent
    };

    const res = await postFetch(ANSWER_POST_URL, newData, accessToken);
    // const headerLocation = res.headers.get('Location').slice(4); // '/questions/49/add/37'
    // const location = headerLocation.slice(0, headerLocation.indexOf('/add')); // '/questions/49'
    // console.log(location);

    if (res) {
      console.log('답변 등록 후 location 확인 필요');
      setAnswerContent('');
      // navigate(location);
      window.history.go(0);
    }
  };

  const checkLogin = () => {
    if (!isLoggedIn) {
      alert('Please log in first to post your answer.');
      navigate('/users/login');
    }
  };

  return (
    <form onSubmit={onSubmit} className={styles.answerPostWrapper}>
      <h2 className={styles.answerHeader}>Your Answer</h2>
      <Editor content={answerContent} setInputs={setAnswerContent} />
      <div className={styles.submitBtn}>
        <Button text='Post your question' handleClick={checkLogin} />
      </div>
    </form>
  );
}

export default CreateAnswer;
