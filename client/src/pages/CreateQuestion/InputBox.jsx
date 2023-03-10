import styles from './InputBox.module.css';

function InputBox({ label, desc, value, onChange, inputId, inputName, placeholder, maxLength }) {
  return (
    <div className={styles.container}>
      <label htmlFor={inputId} className={styles.labelTitle}>
        {label}
      </label>
      <label htmlFor={inputId} className={styles.description}>
        {desc}
      </label>
      <input
        className={styles.input}
        value={value}
        onChange={onChange}
        id={inputId}
        name={inputName}
        type='text'
        maxLength={maxLength}
        placeholder={placeholder}
      ></input>
    </div>
  );
}

export default InputBox;
