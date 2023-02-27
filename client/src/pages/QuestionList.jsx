import { useState, useEffect } from 'react';
import { getFetch } from '../hooks/API/API.js';
import Nav from '../components/Nav.jsx';
import Footer from '../components/Footer.jsx';
import Questions from '../components/Questions.jsx';
import Pagination from '../components/Pagination.jsx';
import styles from './QuestionList.module.css';
import Button from '../components/UI/Button.jsx';

function QuestionList() {
  const [currentPage, setCurrentPage] = useState(1);
  const [questions, setQuestions] = useState([]);
  const [pageInfo, setPageInfo] = useState({});

  const QUESTION_LIST_GET_URL = `${process.env.REACT_APP_URL}/questions?page=${currentPage}&size=10`;

  useEffect(() => {
    getFetch(QUESTION_LIST_GET_URL, setQuestions, setPageInfo, 'pageInfo');
  }, []);

  const { totalElements } = pageInfo;

  return (
    <>
      <div className={styles.container}>
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
            <div className={styles.questionNumber}>{totalElements} questions</div>
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
