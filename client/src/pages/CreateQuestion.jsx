import { useState } from 'react';
import Editor from '../components/UI/Editor.jsx';
import Button from '../components/UI/Button.jsx';
import Footer from '../components/Footer.jsx';
import styles from './CreateQuestion.module.css';

function CreateQuestion() {
  const [inputs, setInputs] = useState({
    title: '',
    content: '',
    tag: ''
  });
  const { title, content, tag } = inputs;

  const onChange = (e) => {
    const { name, value } = e.target;
    setInputs({ ...inputs, [name]: value });
  };
  // console.log(inputs);
  // {title: 'title', content: '', tag: 'ios, js'}

  // ! tag 스트링 값 배열(string)로 바꿔줘야함
  // post 요청시
  //     if (name === 'tag') {
  //   console.log(value);
  //   const arr = value.split(',');
  //   setInputs({ ...inputs, tag: arr });
  // }
  console.log(inputs.tag);
  const tagArr = inputs.tag.split(',');
  console.log(tagArr);

  return (
    <>
      <div className={styles.container}>
        <div className={styles.content}>
          <form id={styles.postForm}>
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
              <div className={`${styles.tagBox} ${styles.boxBorder}`}>
                <label htmlFor='tag' className={styles.labelTitle}>
                  Tags
                </label>
                <label htmlFor='tag' className={styles.description}>
                  Be specific and imagine you’re asking a question to another person.
                </label>
                <input
                  className={styles.input}
                  value={tag}
                  onChange={onChange}
                  id='tag'
                  name='tag'
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
