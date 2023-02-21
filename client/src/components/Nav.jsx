import styles from './Nav.module.css';
import navExploreLogo from '../assets/navExplore.png';
import navILogo from '../assets/navCollectives.png';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'; //폰트어썸 임포트
// import { faEarthAmericas } from '@fortawesome/free-solid-svg-icons'; //폰트어썸 내의 아메리카 이모지 임포트

function Nav() {
  return (
    <nav id={styles.nav}>
      <div>
        <ol id={styles.ol}>
          <li id={styles.li1}>Home</li>
          <li id={styles.li1}>PUBLIC</li>
          <li>
            <ol>
              <li id={styles.li2}>
                {/* <FontAwesomeIcon icon={faEarthAmericas} /> */}
                Questions
              </li>
              <li id={styles.li3}>Tags</li>
              <li id={styles.li3}>Users</li>
              <li id={styles.li3}>Companies</li>
              <li id={styles.li4}>
                COLLECTIVES
                <img id={styles.img} src={navILogo} alt='nnavCollectievs' />
              </li>
              <li id={styles.li5}>
                <img id={styles.img} src={navExploreLogo} alt='navExploreLogo' />
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
