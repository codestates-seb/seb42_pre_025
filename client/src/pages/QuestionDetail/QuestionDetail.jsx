import { useEffect, useState, useContext } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import DOMPurify from 'dompurify';
import { getFetch, deleteFetch } from '../../util/API';
import { userContext } from '../../App';
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

  // * question GET 요청 로직
  const QUESTION_DETAIL_URL = `${process.env.REACT_APP_URL}/questions/${id}`;
  useEffect(() => {
    async function getData() {
      const res = await getFetch(QUESTION_DETAIL_URL);
      setQuestion(res.data);
      // setAnswers(res.data.answers);
    }
    getData();
  }, []);
  // }, [question, answers]);

  console.log(question);

  // TODO: 서버 answerCount 변수 조정 기다리기 (answerCounter or answers 둘 중 하나 쓰면 됨)
  const { title, content, tags, owner, answers: answerArr } = question;
  console.log(question);
  console.log(answers);
  const answerArrLen = answerArr && answerArr.length;

  // TODO: owner 에서 구조분해할당으로 변수 꺼내오는 법 있을지?
  // const { userId, displayName: userName } = owner;
  // const userId = owner && owner.userId;
  const userName = owner && owner.displayName;

  // TODO: 작성자에게만 edit, delete 버튼이 뜨도록 해야함
  const { tokens } = useContext(userContext);
  const accessToken = tokens && tokens.accessToken;

  const handleDelete = async (url) => {
    const result = confirm('Delete this post?');

    if (result) {
      const res = await deleteFetch(url, accessToken);
      if (res.ok) {
        if (url.includes('questions')) {
          navigate('/questions');
        } else {
          navigate(`/questions/${id}`);
        }
      }
    }
  };

  // * answer GET 요청 로직
  const ANSWER_GET_URL = `${process.env.REACT_APP_URL}/questions/${id}/answers`;
  useEffect(() => {
    async function getData() {
      const res = await getFetch(ANSWER_GET_URL);
      setAnswers(res.data);

      if (res) {
        navigate(`/questions/${id}`);
      }
    }
    getData();
  }, []);

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
          {/* {answerCount > 0 && (
            <div className={styles.answerBoxWrapper}>
              <h2 className={styles.answerCount}>
                {answerCount === 1 ? '1 Answer' : `${answerCount} Answers`}
              </h2>
              <AnswerList answers={answers} handleDelete={handleDelete} />
            </div>
          )} */}
          {answerArrLen > 0 && (
            <div className={styles.answerBoxWrapper}>
              <h2 className={styles.answerCount}>
                {answerArrLen === 1 ? '1 Answer' : `${answerArrLen} Answers`}
              </h2>
              <AnswerList answers={answers} handleDelete={handleDelete} />
            </div>
          )}
          <CreateAnswer />
          {/* <form onSubmit={onSubmit} className={styles.answerPostWrapper}>
            <h2 className={styles.answerHeader}>Your Answer</h2>
            <Editor content={answerContent} setInputs={setAnswerContent} />
            <div className={styles.submitBtn}>
              <Button text='Post your question' />
            </div>
          </form> */}
        </main>
      </div>
      <Footer />
    </>
  );
}

export default QuestionDetail;
