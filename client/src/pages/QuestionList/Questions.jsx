import { Link } from 'react-router-dom';
import styles from './Questions.module.css';

function Questions({ question }) {
  const { questionId, title, content, tags, answerCount, userDto } = question;
  const userName = userDto && userDto.displayName;

  // html 태그 제거하고 텍스트만 추출하는 정규식
  const text = content.replace(/(<([^>]+)>)/gi, '');

  return (
    <div className={styles.questionList}>
      <div className={styles.question}>
        <div className={styles.content}>
          <div className={styles.contentDetail}>
            <div className={styles.voteAnswer}>0 votes</div>
            <div className={styles.voteAnswer}>{answerCount} answers</div>
          </div>
          <div className={styles.contentBox}>
            <div className={styles.contentHeadBox}>
              <Link to={`/questions/${questionId}`} className={styles.contentHead}>
                {title}
              </Link>
            </div>
            <div className={styles.contentBody}>{text}</div>
            <div className={styles.contentTagAndUser}>
              <div className={styles.contentTagBox}>
                <div>
                  {tags &&
                    tags.map((tag) => (
                      <button className={styles.contentTag} key={tag}>
                        {tag}
                      </button>
                    ))}
                </div>
              </div>
              <div className={styles.contentUser}>
                <span>{userName}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Questions;
