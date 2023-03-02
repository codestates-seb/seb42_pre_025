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
            <li className={styles.third}>Help</li>
          </ol>
        </div>
        <div className={styles.thirdBox}>
          <ol className={styles.title}>
            PRODUCTS
            <li className={styles.secondTitle}>Teams</li>
            <li className={styles.third}>Advertising</li>
            <li className={styles.third}>Collectives</li>
            <li className={styles.third}>Talent</li>
          </ol>
        </div>
        <div className={styles.forthBox}>
          <ol className={styles.title}>
            COMPANY
            <li className={styles.secondTitle}>About</li>
            <li className={styles.third}>Press</li>
            <li className={styles.third}>Work Here</li>
            <li className={styles.third}>Legal</li>
            <li className={styles.third}>Privacy Policy</li>
            <li className={styles.third}>Terms of Service</li>
            <li className={styles.third}>Contact Us</li>
            <li className={styles.third}>Cookie Setting</li>
            <li className={styles.third}>Cookie Policy</li>
          </ol>
        </div>
        <div className={styles.fifthBox}>
          <ol className={styles.title}>
            STACK EXCHANGE NETWORK
            <li className={styles.secondTitle}>Technology</li>
            <li className={styles.third}>Culture & recreation</li>
            <li className={styles.third}>Life & arts</li>
            <li className={styles.third}>Science</li>
            <li className={styles.third}>Professional</li>
            <li className={styles.third}>Business</li>
            <div className={styles.api}>API</div>
            <li className={styles.third}>Data</li>
          </ol>
        </div>
        <div className={styles.sixthBox}>
          <ul className={styles.sixthBoxSNS}>
            <li className={styles.snsFirst}>Blog</li>
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
