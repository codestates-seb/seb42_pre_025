import { Link } from 'react-router-dom';
import styles from './Footer.module.css';
import Logo from '../assets/logo.png';

function Footer() {
  return (
    <footer id={styles.footer}>
      <div className={styles.container}>
        <div className={styles.firstBox}>
          <Link to='/' className={styles.logo}>
            <img className={styles.img} src={Logo} alt='Logo' />
          </Link>
        </div>
        <div className={styles.secondBox}>
          <ol className={styles.title}>
            STACK OVERFLOW
            <li className={styles.secondTitle}>Questions</li>
            <li>Help</li>
          </ol>
        </div>
        <div className={styles.thirdBox}>
          <ol className={styles.title}>
            PRODUCTS
            <li className={styles.secondTitle}>Teams</li>
            <li>Advertising</li>
            <li>Collectives</li>
            <li>Talent</li>
          </ol>
        </div>
        <div className={styles.forthBox}>
          <ol className={styles.title}>
            COMPANY
            <li className={styles.secondTitle}>About</li>
            <li>Press</li>
            <li>Work Here</li>
            <li>Legal</li>
            <li>Privacy Policy</li>
            <li>Terms of Service</li>
            <li>Contact Us</li>
            <li>Cookie Setting</li>
            <li>Cookie Policy</li>
          </ol>
        </div>
        <div className={styles.fifthBox}>
          <ol className={styles.title}>
            STACK EXCHANGE NETWORK
            <li className={styles.secondTitle}>Technology</li>
            <li>Culture & recreation</li>
            <li>Life & arts</li>
            <li>Science</li>
            <li>Professional</li>
            <li>Business</li>
            <div className={styles.api}>API</div>
            <li>Data</li>
          </ol>
        </div>
        <div className={styles.sixthBox}>
          <ul className={styles.sixthBoxSNS}>
            <li>Blog</li>
            <li className={styles.snsMargin}>Facebook</li>
            <li className={styles.snsMargin}>Twitter</li>
            <li className={styles.snsMargin}>Linkedin</li>
            <li className={styles.snsMargin}>Instagram</li>
          </ul>
          <div className={styles.sixthDetail}>
            Site design / logo © 2023 Stack Exchange Inc; user contributions licensed under CC
            BY-SA. rev 2023.2.23.43256
          </div>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
