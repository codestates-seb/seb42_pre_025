import { Link } from 'react-router-dom';
import styles from './Questions.module.css';

function Questions({ Question }) {
  const { score, title, answercount, content, tag, displayName } = Question;

  return (
    <div className={styles.questionList}>
      <div className={styles.question}>
        <div className={styles.content}>
          <div className={styles.contentDetail}>
            <div className={styles.voteAnswer}>{score} votes</div>
            <div className={styles.voteAnswer}>{answercount} answers</div>
          </div>
          <div>
            <div className={styles.contentHeadBox}>
              <Link to='/questions/detail' className={styles.contentHead}>
                {title}
              </Link>
            </div>
            <div className={styles.contentBody}>{content}</div>
            <div className={styles.contentTagAndUser}>
              <div className={styles.contentTagBox}>
                <div>
                  {tag.map((tag) => (
                    <button className={styles.contentTag} key={tag}>
                      {tag}
                    </button>
                  ))}
                </div>
              </div>
              <div className={styles.contentUser}>
                <span>{displayName}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Questions;
