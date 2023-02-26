import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { getFetch } from '../hooks/API/API';
import styles from './QuestionDetail.module.css';
import Nav from '../components/Nav.jsx';
import Footer from '../components/Footer.jsx';
import Button from '../components/UI/Button.jsx';
import Vote from '../components/Vote.jsx';
import UserLogo from '../assets/logo.png';

function QuestionDetail() {
  const { id } = useParams();
  const [question, setQuestion] = useState({});

  const QUESTION_DETAIL_GET_URL = `${process.env.REACT_APP_URL}/questions/${id}`;

  useEffect(() => {
    getFetch(QUESTION_DETAIL_GET_URL, setQuestion);
  }, []);

  // * answerCount 는 답변 컴포넌트 붙인 다음에 question에서 변수로 빼오기
  const { title, content, tags, userDto } = question;
  const userName = userDto && userDto.displayName;

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
            <div>
              <div>
                <div dangerouslySetInnerHTML={{ __html: content }}></div>
                <div className={styles.questionTag}>{tags && tags.map((tag) => tag)}</div>
                <div className={styles.questionOption}>
                  <div>Edit Delete</div>
                  <div className={styles.user}>
                    <div className={styles.userImg}>
                      <img className={styles.img} src={UserLogo} alt='navILogo' />
                    </div>
                    <div className={styles.userName}>{userName}</div>
                  </div>
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
