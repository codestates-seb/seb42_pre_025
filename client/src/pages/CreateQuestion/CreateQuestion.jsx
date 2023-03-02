import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { postFetch } from '../../util/API.js';
import useAccessToken from '../../util/useAccessToken.js';
import useCheckLogin from '../../util/useCheckLogin.js';
import Editor from '../../components/UI/Editor.jsx';
import InputBox from './InputBox.jsx';
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

  // 로그인되어 있지 않으면 로그인 페이지로 리디렉션
  useCheckLogin();

  const accessToken = useAccessToken();

  const onSubmit = async (e) => {
    e.preventDefault();
    if (title === '' || content === '') {
      alert('Title and body can cannot be empty.');
      return;
    }

    let tags;
    if (inputs.tags.split(',').length === 1 && inputs.tags.split(',')[0] === '') {
      tags = [];
    } else tags = inputs.tags.split(',');

    const newData = {
      title,
      content,
      tags
    };

    // * question POST 요청
    const QUESTION_POST_URL = `${process.env.REACT_APP_URL}/questions`;

    const res = await postFetch(QUESTION_POST_URL, newData, accessToken);
    const location = res.headers.get('Location').slice(4); // 'questions/51', '/api' 삭제

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
              <InputBox
                label='Title'
                desc='Be specific and imagine you’re asking a question to another person.'
                value={title}
                onChange={onChange}
                inputId='title'
                inputName='title'
                placeholder='e.g. Is there an R function for finding the index of an element in a vector?'
                maxLength='300'
              />
              <div className={styles.boxBorder}>
                <label htmlFor='body' className={styles.labelTitle}>
                  What are the details of your problem?
                </label>
                <label htmlFor='body' className={styles.description}>
                  Introduce the problem and expand on what you put in the title. Minimum 20
                  characters.
                </label>
                <Editor content={content} inputs={inputs} setInputs={setInputs} />
              </div>
              <InputBox
                label='Tags'
                desc='Be specific and imagine you’re asking a question to another person.'
                value={tags}
                onChange={onChange}
                inputId='tags'
                inputName='tags'
                placeholder='e.g. (json, node.js, python)'
              />
              <div>
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
