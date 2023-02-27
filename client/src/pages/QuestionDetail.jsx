import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { getFetch, deleteFetch } from '../hooks/API/API';
import Nav from '../components/Nav.jsx';
import Footer from '../components/Footer.jsx';
import Button from '../components/UI/Button.jsx';
import Vote from '../components/Vote.jsx';
import styles from './QuestionDetail.module.css';
import UserLogo from '../assets/logo.png';

function QuestionDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [question, setQuestion] = useState({});

  const QUESTION_DETAIL_URL = `${process.env.REACT_APP_URL}/questions/${id}`;

  useEffect(() => {
    getFetch(QUESTION_DETAIL_URL, setQuestion);
  }, []);

  // * answerCount 는 답변 컴포넌트 붙인 다음에 question에서 변수로 빼오기
  const { title, content, tags, userDto } = question;
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

  return (
    <>
      <div className={styles.container}>
        <Nav />
        <main className={styles.detailBox}>
          <ol className={styles.questionHead}>
            <li>
              <div className={styles.questionTitle}>{title}</div>
            </li>
            <li>
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
                dangerouslySetInnerHTML={{ __html: content }}
                className={styles.contentBox}
              ></div>
              <div className={styles.questionTag}>{tags && tags.map((tag) => tag)}</div>
              <div className={styles.questionOption}>
                <div>
                  <button className={styles.buttonStyle}>Edit</button>
                  <button className={styles.buttonStyle} onClick={handleDelete}>
                    Delete
                  </button>
                </div>
                <div className={styles.user}>
                  <div className={styles.userImg}>
                    <img className={styles.img} src={UserLogo} alt='navILogo' />
                  </div>
                  <div className={styles.userName}>{userName}</div>
                </div>
              </div>
            </div>
          </div>
          <ol className={styles.questionAnswer}>
            <li>답변</li>
          </ol>
        </main>
      </div>
      <Footer />
    </>
  );
}

export default QuestionDetail;
