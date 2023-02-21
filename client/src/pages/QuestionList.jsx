import Nav from '../components/Nav.jsx';
import Footer from '../components/Footer.jsx';
import styles from './QuestionList.module.css';

function QuestionList() {
  return (
    <>
      <div className={styles.container}>
        <Nav />
        <ul id={styles.list}>
          <li>아이템 1</li>
          <li>아이템 2</li>
          <li>아이템 3</li>
        </ul>
      </div>
      <Footer />
    </>
  );
}

export default QuestionList;
