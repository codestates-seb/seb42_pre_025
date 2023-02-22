import styles from './Questions.module.css';

function Questions() {
  return (
    <div className={styles.questionList}>
      <div className={styles.question}>
        <div className={styles.content}>
          <div className={styles.contentDetail}>
            <div>10 votes</div>
            <div>88 answers</div>
          </div>
          <div>
            <div className={styles.contentHead}>
              How can I set the default json object in the controller method?
            </div>
            <div className={styles.contentBody}>
              Good afternoon. At the moment I have such a problem. I need the json to be read in the
              controller, not in the View. But, but there is a problem that SearchFilterSettings is
              not valid. What should I do
            </div>
            <div className={styles.contentTag}>tag</div>
            <div className={styles.contentUser}>사용자Rand Random</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Questions;