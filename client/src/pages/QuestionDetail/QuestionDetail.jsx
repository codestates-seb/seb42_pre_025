import { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import DOMPurify from 'dompurify';
import { getFetch, deleteFetch } from '../../util/API';
import useAccessToken from '../../util/useAccessToken';
import Nav from '../../components/Nav.jsx';
import Footer from '../../components/Footer.jsx';
import Button from '../../components/UI/Button.jsx';
import Vote from './Vote.jsx';
import AnswerList from './AnswerList.jsx';
import CreateAnswer from './CreateAnswer.jsx';
import styles from './QuestionDetail.module.css';
import UserLogo from '../../assets/logo.png';

function QuestionDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [question, setQuestion] = useState({});
  const [answers, setAnswers] = useState([]);

  // * question & question GET 요청
  const QUESTION_DETAIL_URL = `${process.env.REACT_APP_URL}/questions/${id}`;
  useEffect(() => {
    async function getData() {
      const res = await getFetch(QUESTION_DETAIL_URL);
      setQuestion(res.data);
      setAnswers(res.data.answers);
    }
    getData();
  }, []);

  const { title, content, tags, owner, answers: answerArr } = question;
  const answerArrLen = answerArr && answerArr.length;
  const userName = owner && owner.displayName;

  const accessToken = useAccessToken();

  const handleDelete = async (url) => {
    const result = confirm('Delete this post?');

    if (result) {
      const res = await deleteFetch(url, accessToken);
      if (res.ok) {
        if (url.includes('questions')) {
          navigate('/questions');
        } else {
          window.location.reload();
        }
      }
    }
  };

  return (
    <>
      <div className={styles.container}>
        <Nav />
        <main className={styles.detailBox}>
          <ol className={styles.questionHead}>
            <li>
              <div className={styles.questionTitle}>{title}</div>
            </li>
            <li className={styles.askButton}>
              <Button
                text='Ask Question'
                path='/questions/ask'
                addStyle={{
                  padding: '11px'
                }}
              />
            </li>
          </ol>
          <div className={styles.questionBody}>
            <Vote />
            <div className={styles.box}>
              <div
                dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(content) }}
                className={styles.contentBox}
              ></div>
              <div className={styles.questionTag}>
                {tags &&
                  tags.map((tag) => (
                    <button className={styles.contentTag} key={tag}>
                      {tag}
                    </button>
                  ))}
              </div>
              <div className={styles.questionOption}>
                <div>
                  <Link to={'/questions/edit'}>
                    <button className={styles.buttonStyle}>Edit</button>{' '}
                  </Link>
                  <button
                    className={styles.buttonStyle}
                    onClick={() => handleDelete(QUESTION_DETAIL_URL)}
                  >
                    Delete
                  </button>
                </div>
                <div className={styles.user}>
                  <div className={styles.userImg}>
                    <img className={styles.img} src={UserLogo} alt='user logo' />
                  </div>
                  <div className={styles.userName}>{userName}</div>
                </div>
              </div>
            </div>
          </div>
          {answerArrLen > 0 && (
            <div className={styles.answerBoxWrapper}>
              <h2 className={styles.answerCount}>
                {answerArrLen === 1 ? '1 Answer' : `${answerArrLen} Answers`}
              </h2>
              <AnswerList answers={answers} handleDelete={handleDelete} />
            </div>
          )}
          <CreateAnswer />
        </main>
      </div>
      <Footer />
    </>
  );
}

export default QuestionDetail;
