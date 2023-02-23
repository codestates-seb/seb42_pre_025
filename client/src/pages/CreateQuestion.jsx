import ToastEditor from "../components/UI/ToastEditor.jsx";
import Footer from "../components/Footer.jsx";
import styles from "./CreateQuestion.module.css";

function CreateQuestion() {
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
                    You’re ready to <span>ask</span> a{" "}
                    <span>programming-related question</span> and this form will
                    help guide you through the process.
                  </p>
                  <p>
                    Looking to ask a non-programming question? See{" "}
                    <span>the topics here</span> to find a relevant site.
                  </p>
                  <h5>Steps</h5>
                  <ul>
                    <li>Summarize your problem in a one-line title.</li>
                    <li>Describe your problem in more detail.</li>
                    <li>
                      Describe what you tried and what you expected to happen.
                    </li>
                    <li>
                      Add “tags” which help surface your question to members of
                      the community.
                    </li>
                    <li>Review your question and post it to the site.</li>
                  </ul>
                </div>
              </div>
              <div className={`${styles.titleBox} ${styles.boxBorder}`}>
                <label htmlFor="title" className={styles.labelTitle}>
                  Title
                </label>
                <label htmlFor="title" className={styles.description}>
                  Be specific and imagine you’re asking a question to another
                  person.
                </label>
                <input
                  className={styles.input}
                  id="title"
                  name="title"
                  type="text"
                  maxLength="300"
                  placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
                ></input>
              </div>
              <div className={`${styles.bodyBox} ${styles.boxBorder}`}>
                <ToastEditor />
              </div>
              <div className={`${styles.tagBox} ${styles.boxBorder}`}>
                <label htmlFor="tag" className={styles.labelTitle}>
                  Tags
                </label>
                <label htmlFor="tag" className={styles.description}>
                  Be specific and imagine you’re asking a question to another
                  person.
                </label>
                <input
                  className={styles.input}
                  id="tag"
                  name="tag"
                  type="text"
                  maxLength="300"
                  placeholder="e.g. (json, node.js, python)"
                ></input>
              </div>
              <div className={styles.formSubmit}></div>
            </main>
          </form>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default CreateQuestion;
