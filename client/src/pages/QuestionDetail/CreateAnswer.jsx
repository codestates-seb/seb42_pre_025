import { useState, useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { userContext } from '../../App.js';
import useAccessToken from '../../util/useAccessToken';
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

  const onSubmit = async (e) => {
    e.preventDefault();
    if (answerContent === '') {
      alert('Body can cannot be empty.');
      return;
    }
    const newData = {
      // userId,
      content: answerContent
    };

    // * answer POST 요청
    const ANSWER_POST_URL = `${process.env.REACT_APP_URL}/questions/${id}/add`;
    const accessToken = useAccessToken();
    const res = await postFetch(ANSWER_POST_URL, newData, accessToken);
    // const headerLocation = res.headers.get('Location').slice(4); // '/questions/49/add/37'
    // const location = headerLocation.slice(0, headerLocation.indexOf('/add')); // '/questions/49'
    // console.log(location);

    // ! 답변 등록 후 페이지 새로고침 시 state 유지되는지 확인 필요
    if (res) {
      setAnswerContent('');
      // navigate(location);
      window.location.reload();
      // window.history.go(0);
    }
  };

  const { isLoggedIn } = useContext(userContext);
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
