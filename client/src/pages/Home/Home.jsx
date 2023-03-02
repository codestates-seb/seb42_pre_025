import Footer from '../../components/Footer.jsx';
import styles from './Home.module.css';
import HomePage from '../../assets/Home.png';

function Home() {
  return (
    <>
      <img className={styles.img} src={HomePage} alt='HomePage' />
      <Footer />
    </>
  );
}

export default Home;
