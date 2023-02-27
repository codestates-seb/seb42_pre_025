import { Link } from 'react-router-dom';
import styles from './Nav.module.css';
import navExploreLogo from '../assets/navExplore.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faInfoCircle } from '@fortawesome/free-solid-svg-icons';

function Nav() {
  return (
    <nav id={styles.nav}>
      <div>
        <ol className={styles.ol}>
          <li className={styles.li1}>Home</li>
          <li className={styles.li1}>PUBLIC</li>
          <li>
            <ol>
              <li className={styles.questionsBox}>
                <Link to={'/questions'} className={styles.questions}>
                  Questions
                </Link>
              </li>
              <li className={styles.li2}>Tags</li>
              <li className={styles.li2}>Users</li>
              <li className={styles.li2}>Companies</li>
              <li className={styles.li1}>
                <div className={styles.collectives}>COLLECTIVES</div>
                <FontAwesomeIcon className={styles.imgI} icon={faInfoCircle} />
              </li>
              <li className={styles.li3}>
                <img className={styles.img} src={navExploreLogo} alt='navExploreLogo' />
                Explore Collectives
              </li>
            </ol>
          </li>
        </ol>
      </div>
    </nav>
  );
}

export default Nav;
