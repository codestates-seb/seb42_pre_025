import Nav from '../components/Nav.jsx';
import Footer from '../components/Footer.jsx';
import Questions from '../components/Questions.jsx';
import Pagination from '../components/Pagination.jsx';
import styles from './QuestionList.module.css';
import Button from '../components/UI/Button.jsx';

function QuestionList() {
  return (
    <>
      <div className={styles.container}>
        <Nav />
        <div className={styles.div}>
          <div className={styles.border}>
            <div className={styles.allQuestionsBox}>
              <div className={styles.allQuestions}>All Questions</div>
              <Button
                text='Ask Question'
                path='/questions/ask'
                addStyle={{
                  width: '103px',
                  padding: '0px 10px'
                }}
              />
            </div>
            <div className={styles.questionNumber}>23,510,472 questions</div>
          </div>
          <Questions />
          <Pagination />
        </div>
      </div>
      <Footer />
    </>
  );
}

export default QuestionList;
