import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import DOMPurify from 'dompurify';
import { getFetch, postFetch, deleteFetch } from '../../hooks/API';
import Nav from '../../components/Nav.jsx';
import Footer from '../../components/Footer.jsx';
import Button from '../../components/UI/Button.jsx';
import Vote from './Vote.jsx';
import Editor from '../../components/UI/Editor.jsx';
import styles from './QuestionDetail.module.css';
import UserLogo from '../../assets/logo.png';

function QuestionDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [question, setQuestion] = useState({});
  // const [answers, setAnswers] = useState([]);
  const [answerContent, setAnswerContent] = useState('');
  console.log(answerContent);

  // * question GET 요청 로직
  const QUESTION_DETAIL_URL = `${process.env.REACT_APP_URL}/questions/${id}`;
  useEffect(() => {
    getFetch(QUESTION_DETAIL_URL, setQuestion);
  }, []);

  // * answerCount 는 답변 컴포넌트 붙인 다음에 question에서 변수로 빼오기
  const { questionId, title, content, tags, userDto, answerCount } = question;
  console.log(answerCount);
  // ? userDto에서 구조분해할당으로 변수 꺼내오는 법 있을지?
  const userId = userDto && userDto.userId;
  const userName = userDto && userDto.displayName;

  // ! 작성자에게만 edit, delete 버튼이 뜨도록 해야함
  const handleDelete = async () => {
    const result = confirm('Delete this post?');

    if (result === true) {
      const res = await deleteFetch(QUESTION_DETAIL_URL);
      // 상태 코드 204
      if (res.ok) {
        navigate('/questions');
      }
    }
  };

  // * answer POST 요청 로직
  const ANSWER_POST_URL = `${process.env.REACT_APP_URL}/questions/${questionId}/add`;
  // console.log(ANSWER_POST_URL);

  const onSubmit = async (e) => {
    e.preventDefault();
    // ! input 값에 빈 문자열 들어올 때 사용자에게 알림 처리해줘야 함
    if (answerContent === '') return;

    const newData = {
      userId,
      content: answerContent
    };
    // console.log(newData);

    const res = await postFetch(ANSWER_POST_URL, newData);
    console.log(res);
    if (res) {
      navigate(`/questions/${questionId}`);
    }
  };

  // * answer GET 요청 로직
  // const ANSWER_GET_URL = `${process.env.REACT_APP_URL}/questions/${questionId}/add`;

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
                  <button className={styles.buttonStyle}>Edit</button>
                  <button className={styles.buttonStyle} onClick={handleDelete}>
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
          {answerCount === 0 ? (
            <form onSubmit={onSubmit} className={styles.answerWrapper}>
              <h2 className={styles.answerHeader}>Your Answer</h2>
              <Editor content={answerContent} setInputs={setAnswerContent} />
              <div className={styles.submitBtn}>
                <Button text='Post your question' />
              </div>
            </form>
          ) : null}
        </main>
      </div>
      <Footer />
    </>
  );
}

export default QuestionDetail;
