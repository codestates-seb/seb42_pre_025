import { useState, useEffect } from 'react';
import { getFetch } from '../../util/API.js';
import Nav from '../../components/Nav.jsx';
import Footer from '../../components/Footer.jsx';
import Questions from './Questions.jsx';
import Pagination from './Pagination.jsx';
import Button from '../../components/UI/Button.jsx';
import styles from './QuestionList.module.css';

function QuestionList() {
  const [currentPage, setCurrentPage] = useState(1);
  const [questions, setQuestions] = useState([]);
  const [pageInfo, setPageInfo] = useState({});

  const QUESTION_LIST_GET_URL = `${process.env.REACT_APP_URL}/questions?page=${currentPage}&size=10&sort=questionId,desc`;

  useEffect(() => {
    // getFetch(QUESTION_LIST_GET_URL, setQuestions, setPageInfo, 'pageInfo');
    async function getData() {
      const res = await getFetch(QUESTION_LIST_GET_URL);
      setQuestions(res.data);
      setPageInfo(res.pageInfo);
    }
    getData();
  }, [currentPage]);

  const { totalElements } = pageInfo;
  // console.log(questions);

  return (
    <>
      <div id={styles.questionList}>
        <Nav />
        <div className={styles.div}>
          <div className={styles.border}>
            <div className={styles.allQuestionsBox}>
              <div className={styles.allQuestions}>All Questions</div>
              <div>
                <Button
                  text='Ask Question'
                  path='/questions/ask'
                  addStyle={{
                    padding: '11px'
                  }}
                />
              </div>
            </div>
            <div className={styles.questionNumber}>
              {/* {totalElements ? `${totalElements} questions` : '0 question'} */}
              {totalElements === 1
                ? '1 question'
                : totalElements >= 2
                ? `${totalElements} questions`
                : '0 question'}
            </div>
          </div>
          {questions &&
            questions.map((question) => (
              <Questions key={question.questionId} question={question} />
            ))}
          <Pagination
            currentPage={currentPage}
            setCurrentPage={setCurrentPage}
            totalElements={totalElements}
          />
        </div>
      </div>
      <Footer />
    </>
  );
}

export default QuestionList;
