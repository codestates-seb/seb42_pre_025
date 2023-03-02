import { useState, useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { userContext } from '../../App.js';
import useAccessToken from '../../util/useAccessToken';
import { postFetch } from '../../util/API';
import Editor from '../../components/UI/Editor.jsx';
import Button from '../../components/UI/Button.jsx';
import styles from './CreateAnswer.module.css';

function CreateAnswer() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [answerContent, setAnswerContent] = useState('');

  const { isLoggedIn } = useContext(userContext);
  const accessToken = useAccessToken();

  const onSubmit = async (e) => {
    e.preventDefault();
    if (answerContent === '') {
      alert('Body can cannot be empty.');
      return;
    }
    const newData = {
      content: answerContent
    };

    // * answer POST 요청
    const ANSWER_POST_URL = `${process.env.REACT_APP_URL}/questions/${id}/add`;
    const res = await postFetch(ANSWER_POST_URL, newData, accessToken);

    if (res) {
      setAnswerContent('');
      window.location.reload();
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
