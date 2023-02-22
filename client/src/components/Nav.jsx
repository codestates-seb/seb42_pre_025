import styles from './Nav.module.css';
import navExploreLogo from '../assets/navExplore.png';
import navILogo from '../assets/navCollectives.png';

function Nav() {
  return (
    <nav id={styles.nav}>
      <div>
        <ol className={styles.ol}>
          <li className={styles.li1}>Home</li>
          <li className={styles.li1}>PUBLIC</li>
          <li>
            <ol>
              <li className={styles.li2}>Questions</li>
              <li className={styles.li2}>Tags</li>
              <li className={styles.li2}>Users</li>
              <li className={styles.li2}>Companies</li>
              <li className={styles.li1}>
                COLLECTIVES
                <img className={styles.imgI} src={navILogo} alt='navCollectives' />
              </li>
              <li className={styles.li1}>
                <img className={styles.img} src={navExploreLogo} alt='navExploreLogo' />
                <span>Explore Collectives</span>
              </li>
            </ol>
          </li>
        </ol>
      </div>
    </nav>
  );
}

export default Nav;
