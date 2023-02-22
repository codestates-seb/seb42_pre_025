import Nav from '../components/Nav.jsx';
import Footer from '../components/Footer.jsx';
import styles from './QuestionList.module.css';

function QuestionList() {
  return (
    <>
      <div className={styles.container}>
        <Nav />
        <div className={styles.div}>
          <div className={styles.border}>
            <div className={styles.allQuestionsBox}>
              <div className={styles.allQuestions}>All Questions</div>
              <button className={styles.askQuesion}>Ask Question</button>
            </div>
            <div className={styles.div2}>23,510,472 questions</div>
          </div>
          <div className={styles.questionList}>
            질문목록
            <div className={styles.question}>
              <div></div>
              <div className={styles.content}></div>
            </div>
          </div>
          <div className={styles.page}>
            <div className={styles.pageButton}>1</div>
            <div className={styles.pageButton}>2</div>
            <div className={styles.pageButton}>3</div>
            <div className={styles.pageButton}>4</div>
            <div className={styles.pageButton}>5</div>
            <div className={styles.pagedot}>....</div>
            <div className={styles.pageNext}>Next</div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default QuestionList;
