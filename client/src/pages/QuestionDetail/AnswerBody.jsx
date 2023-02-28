import DOMPurify from 'dompurify';
import Vote from './Vote.jsx';
import styles from './QuestionDetail.module.css';

function AnswerBody({ id, content, userName, handleDelete }) {
  const ANSWER_DELETE_URL = `${process.env.REACT_APP_URL}/answers/${id}`;

  return (
    <div className={styles.answerBody}>
      <Vote />
      <div className={styles.box}>
        <div>
          <div
            dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(content) }}
            className={styles.contentBox}
          ></div>
        </div>
        <div className={styles.questionOption}>
          <div>
            <button className={styles.buttonStyle}>Edit</button>
            <button className={styles.buttonStyle} onClick={() => handleDelete(ANSWER_DELETE_URL)}>
              Delete
            </button>
          </div>
          <div className={styles.user}>
            {/* <div className={styles.userImg}>
              <img className={styles.img} src={UserLogo} alt='user logo' />
            </div> */}
            <div className={styles.userName}>{userName}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AnswerBody;
