import styles from './Questions.module.css';

function Questions() {
  return (
    <div className={styles.questionList}>
      <div className={styles.question}>
        <div></div>
        <div className={styles.content}></div>
      </div>
    </div>
  );
}

export default Questions;
