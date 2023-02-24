import Nav from '../components/Nav.jsx';
import Footer from '../components/Footer.jsx';
import Questions from '../components/Questions.jsx';
import Pagination from '../components/Pagination.jsx';
import styles from './QuestionList.module.css';
import Button from '../components/UI/Button.jsx';

function QuestionList() {
  const QuestionData = [
    {
      questionid: 1,
      owner: {
        displayName: 'Kelly Bundy',
        profileImage: 'www.ddddd.ssss',
        aboutMe: 'Helllo'
      },
      isanswered: true,
      acceptedanswerid: 1,
      answercount: 3,
      score: 0,
      createdAt: '0000-00-00T00:00:00.0000000',
      modifiedAt: '0000-00-00T00:00:00.0000000',
      title: 'How can I set the default json object in the controller method?',
      content: ' content of the question',
      tag: ['tag1', 'tag2']
    },
    {
      questionid: 2,
      owner: {
        displayName: 'Kelly Bundy',
        profileImage: 'www.ddddd.ssss',
        aboutMe: 'Helllo'
      },
      isanswered: true,
      acceptedanswerid: 1,
      answercount: 3,
      score: 0,
      createdAt: '0000-00-00T00:00:00.0000000',
      modifiedAt: '0000-00-00T00:00:00.0000000',
      title: 'How can I set the default ',
      content: ' content of the question',
      tag: ['tag']
    },
    {
      questionid: 3,
      owner: {
        displayName: 'Kelly Bundy',
        profileImage: 'www.ddddd.ssss',
        aboutMe: 'Helllo'
      },
      isanswered: true,
      acceptedanswerid: 1,
      answercount: 3,
      score: 0,
      createdAt: '0000-00-00T00:00:00.0000000',
      modifiedAt: '0000-00-00T00:00:00.0000000',
      title: ' kladkandoas controller method?',
      content: ' content of the question',
      tag: ['tag1', 'tag2', 'tag3']
    }
  ];

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
            <div className={styles.questionNumber}>23,510,472 questions</div>
          </div>
          {QuestionData.map((Question) => (
            <Questions key={Question.questionid} Question={Question} />
          ))}
          <Pagination />
        </div>
      </div>
      <Footer />
    </>
  );
}

export default QuestionList;
