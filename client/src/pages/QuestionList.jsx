import styles from './QuestionList.module.css';

function QuestionList() {
  return (
    <ul id={styles.list}>
      <li>아이템 1</li>
      <li>아이템 2</li>
      <li>아이템 3</li>
    </ul>
  );
}

export default QuestionList;
