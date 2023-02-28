import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { postFetch } from '../../hooks/API.js';
import Editor from '../../components/UI/Editor.jsx';
import Button from '../../components/UI/Button.jsx';
import Footer from '../../components/Footer.jsx';
import styles from './CreateQuestion.module.css';

function CreateQuestion() {
  const navigate = useNavigate();

  const [inputs, setInputs] = useState({
    title: '',
    content: '',
    tags: ''
  });
  const { title, content, tags } = inputs;

  const onChange = (e) => {
    const { name, value } = e.target;
    setInputs({ ...inputs, [name]: value });
  };

  const QUESTION_POST_URL = `${process.env.REACT_APP_URL}/questions`;

  const onSubmit = async (e) => {
    e.preventDefault();
    // ! input 값에 빈 문자열 들어올 때 사용자에게 알림 처리해줘야 함
    if (title === '' || content === '') return;

    let tags;
    if (inputs.tags.split(',').length === 1 && inputs.tags.split(',')[0] === '') {
      tags = [];
    } else tags = inputs.tags.split(',');

    const newData = {
      title,
      content,
      tags,
      userId: 3,
      password: '1111'
    };
    // console.log(newData);

    const res = await postFetch(QUESTION_POST_URL, newData);
    const location = res.headers.get('Location').slice(4); // '/api' 삭제

    if (res) {
      navigate(location);
    }
  };

  return (
    <>
      <div className={styles.container}>
        <div className={styles.content}>
          <form id={styles.postForm} onSubmit={onSubmit}>
            <main id={styles.questionForm}>
              <div className={styles.writeQuestionNotice}>
                <div className={styles.headline}>
                  <h1>Ask a public question</h1>
                </div>
                <div className={`${styles.notice} ${styles.boxBorder}`}>
                  <h2>Writing a good question</h2>
                  <p>
                    You’re ready to <span>ask</span> a <span>programming-related question</span> and
                    this form will help guide you through the process.
                  </p>
                  <p>
                    Looking to ask a non-programming question? See <span>the topics here</span> to
                    find a relevant site.
                  </p>
                  <h5>Steps</h5>
                  <ul>
                    <li>Summarize your problem in a one-line title.</li>
                    <li>Describe your problem in more detail.</li>
                    <li>Describe what you tried and what you expected to happen.</li>
                    <li>
                      Add “tags” which help surface your question to members of the community.
                    </li>
                    <li>Review your question and post it to the site.</li>
                  </ul>
                </div>
              </div>
              <div className={`${styles.titleBox} ${styles.boxBorder}`}>
                <label htmlFor='title' className={styles.labelTitle}>
                  Title
                </label>
                <label htmlFor='title' className={styles.description}>
                  Be specific and imagine you’re asking a question to another person.
                </label>
                <input
                  className={styles.input}
                  value={title}
                  onChange={onChange}
                  id='title'
                  name='title'
                  type='text'
                  maxLength='300'
                  placeholder='e.g. Is there an R function for finding the index of an element in a vector?'
                ></input>
              </div>
              <div className={`${styles.bodyBox} ${styles.boxBorder}`}>
                <label htmlFor='body' className={styles.labelTitle}>
                  What are the details of your problem?
                </label>
                <label htmlFor='body' className={styles.description}>
                  Introduce the problem and expand on what you put in the title. Minimum 20
                  characters.
                </label>
                <Editor content={content} inputs={inputs} setInputs={setInputs} />
              </div>
              <div className={`${styles.tagsBox} ${styles.boxBorder}`}>
                <label htmlFor='tags' className={styles.labelTitle}>
                  Tags
                </label>
                <label htmlFor='tags' className={styles.description}>
                  Be specific and imagine you’re asking a question to another person.
                </label>
                <input
                  className={styles.input}
                  value={tags}
                  onChange={onChange}
                  id='tags'
                  name='tags'
                  type='text'
                  maxLength='300'
                  placeholder='e.g. (json, node.js, python)'
                ></input>
              </div>
              <div className={styles.formSubmit}>
                <Button text='Post your question' />
              </div>
            </main>
          </form>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default CreateQuestion;
