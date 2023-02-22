import styles from './Pagination.module.css';

function pagination() {
  return (
    <div className={styles.page}>
      <div className={styles.pageButton}>1</div>
      <div className={styles.pageButton}>2</div>
      <div className={styles.pageButton}>3</div>
      <div className={styles.pageButton}>4</div>
      <div className={styles.pageButton}>5</div>
      <div className={styles.pagedot}>....</div>
      <div className={styles.pageNext}>Next</div>
    </div>
  );
}

export default pagination;
