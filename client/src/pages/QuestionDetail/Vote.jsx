import styles from './Vote.module.css';

function VotesButton() {
  return (
    <div>
      <div className={styles.upDownCount}>
        <div className={styles.upDownBox}>
          <div className={styles.upButton}></div>
        </div>
        <div className={styles.count}>0</div>
        <div className={styles.upDownBox}>
          <div className={styles.downButton}></div>
        </div>
      </div>
    </div>
  );
}

export default VotesButton;
