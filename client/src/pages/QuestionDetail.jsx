import styles from './QuestionDetail.module.css';
import Nav from '../components/Nav.jsx';
import Footer from '../components/Footer.jsx';
import Button from '../components/UI/Button.jsx';
import Vote from '../components/Vote.jsx';
import UserLogo from '../assets/logo.png';

function QuestionDetail() {
  return (
    <>
      <div className={styles.container}>
        <Nav />
        <main className={styles.detailBox}>
          <ol className={styles.questionHead}>
            <li>
              <div className={styles.questionTitle}>date differnce between duplicate values</div>
            </li>
            <li>
              <Button
                text='Ask Question'
                path='/questions/ask'
                addStyle={{
                  width: '103px',
                  padding: '10px'
                }}
              />
            </li>
          </ol>
          <div className={styles.questionBody}>
            <Vote />
            <div>
              <div>
                <div>
                  위키백과, 우리 모두의 백과사전. 정보과학에서 더미 데이터는 유용한 데이터가
                  포함되지 않지만 공간을 예비해두어 실제 데이터가 명목상 존재하는 것처럼 다루는
                  유순한 정보를 의미한다. 더미 데이터는 테스트 및 운영 목적을 위해 플레이스홀더로
                  사용할 수 있다. 테스트를 위해 더미 데이터는 모든 변수와 데이터 필드가 채워져
                  있다는 것을 보증함으로써 소프트웨어 테스트 문제를 회피할 목적으로 사용할 수도
                  있다. 운영상의 목적에서 더미 데이터는 OPSEC 목적을 위해 전송이 가능하다. 더미
                  데이터는 예측되지 않은 영향을 주지 않도록 보장하기 위해 엄격히 평가되고
                  문서화되어야 한다.위키백과, 우리 모두의 백과사전. 정보과학에서 더미 데이터는
                  유용한 데이터가 포함되지 않지만 공간을 예비해두어 실제 데이터가 명목상 존재하는
                  것처럼 다루는 유순한 정보를 의미한다. 더미 데이터는 테스트 및 운영 목적을 위해
                  플레이스홀더로 사용할 수 있다. 테스트를 위해 더미 데이터는 모든 변수와 데이터
                  필드가 채워져 있다는 것을 보증함으로써 소프트웨어 테스트 문제를 회피할 목적으로
                  사용할 수도 있다. 운영상의 목적에서 더미 데이터는 OPSEC 목적을 위해 전송이
                  가능하다. 더미 데이터는 예측되지 않은 영향을 주지 않도록 보장하기 위해 엄격히
                  평가되고 문서화되어야 한다.
                </div>
                <div className={styles.questionTag}>
                  <div>Tag</div>
                </div>
                <div className={styles.questionOption}>
                  <div>Edit Delete</div>
                  <div className={styles.user}>
                    <div className={styles.userImg}>
                      <img className={styles.img} src={UserLogo} alt='navILogo' />
                    </div>
                    <div className={styles.userName}>Benidene</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <ol className={styles.questionAnswer}>
            <li>답변</li>
          </ol>
        </main>
      </div>
      <Footer />
    </>
  );
}

export default QuestionDetail;
