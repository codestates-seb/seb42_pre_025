import Nav from '../../components/Nav.jsx';
import Footer from '../../components/Footer.jsx';
import InputBox from './InputBox.jsx';
import Editor from '../../components/UI/Editor.jsx';
import Button from '../../components/UI/Button.jsx';
import styles from './EditQuestion.module.css';

function EditQuestion() {
  return (
    <>
      <div className={styles.container}>
        <Nav />
        <main className={styles.main}>
          <form>
            <InputBox label='Title' inputId='title' inputName='title' />
            <div className={styles.boxBorder}>
              <label htmlFor='body' className={styles.labelTitle}>
                Body
              </label>
              <Editor />
            </div>
            <InputBox label='Tags' inputId='tags' inputName='tags' />
            <div className={styles.btnGroup}>
              <Button text='Save edits' />
              {/* TODO: Cancel 버튼 추가 */}
            </div>
          </form>
        </main>
      </div>
      <Footer />
    </>
  );
}

export default EditQuestion;
