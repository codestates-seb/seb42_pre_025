import styles from './InputBox.module.css';

function InputBox({ label, value, onChange, inputId, inputName }) {
  return (
    <div className={styles.container}>
      <label className={styles.labelTitle} htmlFor={inputId}>
        {label}
      </label>
      <input
        className={styles.input}
        value={value}
        onChange={onChange}
        id={inputId}
        name={inputName}
        type='text'
      ></input>
    </div>
  );
}

export default InputBox;
