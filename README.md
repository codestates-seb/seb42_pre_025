# :headphones: 네? 뉴진스의 하입보이요 Team 입니다.

:bulb: 프로젝트 목표
-
부트캠프 기간동안 학습한 지식들을 응용하여 컴퓨터 프로그래밍의 다양한 주제에 대한 질문과 답변의 기능 서비스를 제공하는 스택 오버플로(Stack Overflow) 를 설계단계부터 프론트엔드-백엔드간 협업하며 프로젝트를 진행하며 진행에 필요한 상호간의 지식을 습득하고 요구사항을 조율하며 협업경험을 체득한다

:paperclip: 배포 주소
-
https://seb42-pre-025-seb42-pre-025.vercel.app/

세부정보 -> 이 안전하지 않은 사이트를 방문 클릭해주세요.

(Stack Overflow를 똑같이 구현한 사이트이므로, 브라우저에서 사기성 사이트 주의 메세지가 출력되고 있습니다.)

:calendar: 프로젝트 기간
-
23.02.13 ~ 23.03.02

:file_folder: 문서함
- 
+ [사용자 요구사항 정의서](https://docs.google.com/spreadsheets/d/1WHwyDLI1bqthNM4a_FT1rRkLSApj1W5WoN8jVh_OHOg/edit#gid=1661060620)
+ [API 명세서](https://docs.google.com/spreadsheets/d/1WHwyDLI1bqthNM4a_FT1rRkLSApj1W5WoN8jVh_OHOg/edit#gid=961764758)
+ [User Flow](https://www.figma.com/file/uNUbBMMRbvj1bIJcq6d0qG/User-Flow?node-id=0%3A1&t=XcrCYRrGDkqZgcME-0)
+ [개발자 테스트](https://docs.google.com/spreadsheets/d/1WHwyDLI1bqthNM4a_FT1rRkLSApj1W5WoN8jVh_OHOg/edit#gid=1232042637)
+ [활동 기록, 회의기록 및 칸반보드](https://www.notion.so/codestates/3df9b9ad27a749d687f360dff69ab7ef)

:family: 팀원 소개
-

### Frontend
|김아애|이현동|김지아|
|:---:|:---:|:---:|
|[Ah-ae](https://github.com/Ah-ae)|[benidene](https://github.com/benidene)|[jia222](https://github.com/jia222)|

### Backend
|김규하|반종현|심현보|
|:---:|:---:|:---:|
|[steadykyu](https://github.com/steadykyu)|[JONGHYUNVAN](https://github.com/JONGHYUNVAN)|[qushe8r](https://github.com/qushe8r)|

:computer: 기술 스택
-
### 공통
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white">

### Frontend
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white"> <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white"> <img src="https://img.shields.io/badge/JAVASCRIPT-F7DF1E?style=for-the-badge&logo=JAVASCRIPT&logoColor=white"> <img src="https://img.shields.io/badge/REACT-008FC7?style=for-the-badge&logo=REACT&logoColor=white"> <img src="https://img.shields.io/badge/VERCEL-000000?style=for-the-badge&logo=VERCEL&logoColor=white"> 

### Backend
<img src="https://img.shields.io/badge/Java-008FC7?style=for-the-badge&logo=Java&logoColor=white"> <img src="https://img.shields.io/badge/SPRING-6DB33F?style=for-the-badge&logo=SPRING&logoColor=white"> <img src="https://img.shields.io/badge/SPRING BOOT-6DB33F?style=for-the-badge&logo=SPRING BOOT&logoColor=white"> <img src="https://img.shields.io/badge/SPRING Security-6DB33F?style=for-the-badge&logo=SPRING Security&logoColor=white"> <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JWT&logoColor=white"> <img src="https://img.shields.io/badge/AMAZON EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white"> <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=PostgreSQL&logoColor=white"> 


:pencil2: 커밋 & 코드 컨벤션
-
| 태그 이름 | 설명 |
| --- | --- |
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| docs | 문서 수정 |
| refactor | 코드 리팩토링 |
| test | 테스트 및 테스트 리팩토링 코드 작성(프로덕션 코드 변경 X) |
| chore | 빌드 업무 수정, 패키지 매니저 설정 및 수정(프로덕션 코드 변경 X) |
| style | 코드 수정은 없지만, 코드 포맷 변경시 작성 |
| design | CSS 등 사용자 UI 디자인 변경 |
| comment | 필요한 주석 추가 및 변경 |
| rename | 파일 및 폴더를 수정하는 작업 |
| remove | 파일 및 폴더를 삭제하는 작업 |
| init | 초기 환경설정 |
| !BREAKING CHANGE | 커다란 API 변경의 경우 |
| !HOTFIX |  급하게 치명적인 버그를 고쳐야 하는 경우 |


  <details>
<summary>코드 컨벤션</summary>
<div markdown="1">

- **변수명, 함수명에는** `camelCase`**를 사용한다.**
- **파일명은 파스칼 케이스를 사용한다.**
- **상수는 대문자로 작성하고 띄어쓰기는 _를 사용한다.**

    ```jsx
    const BASE_URL = 'http://localhost:8080/hello'
    ```

- **컴포넌트 확장자는 `.jsx` 로 한다**
- **컴포넌트 파일 내 `import` 순서는 모듈 → 컴포넌트 → CSS로 한다.**
- **리액트 컴포넌트는 `rfce` 코드스니펫을 사용한다.**
    - 스니펫 익스텐션

        [https://marketplace.visualstudio.com/items?itemName=dsznajder.es7-react-js-snippets](https://marketplace.visualstudio.com/items?itemName=dsznajder.es7-react-js-snippets)

        사용법: [https://www.hanl.tech/blog/vs-code-react-time-awesome-snippets/](https://www.hanl.tech/blog/vs-code-react-time-awesome-snippets/)


    [https://mine-it-record.tistory.com/620](https://mine-it-record.tistory.com/620) 

    [https://basketdeveloper.tistory.com/68](https://basketdeveloper.tistory.com/68)


- ****재할당이 불가능하면 `const`를, 재할당이 가능한 변수는`let`을 사용하여 선언한다.**
- ****`var`는 절대로 사용하지 않도록 한다.**
- **변수를 한꺼번에 여러 개 선언해야 할 경우** ****`const` 를 `let`보다 먼저 선언한다.**
- ⚠️ ****전역 변수를 사용하지 않는다.****
- ****변수 등을 조합해서 문자열을 생성하는 경우 템플릿 문자열(backtick ````)을 이용한다.**
- ****함수는  화살표 함수로 작성한다.****

❌  **변수, 클래스명에는 동사를 넣지 않는다**

```jsx
class: FeatureExtract (X)
class: FeatureExtractor (O)
const: work (X)
const: worker (O)
```

❌ **변수명에 굳이 관사를 넣지 않는다.**

```jsx
const: a_cat (X)
const: cat (O)
```

❌ **변수명에 전치사는 최대한 생략한다.**

```jsx
const : the_number_of_worker (X)
const : worker_num (O)
```

❌ ****배열 복사 시 순환문을 사용하지 않는다.****

복잡한 객체를 복사할 때 **`전개 연산자`**를 사용하면 좀 더 명확하게 정의할 수 있고 가독성이 좋아진다.

```jsx
// Bad
const len = items.length;
let i;
for (i = 0; i < len; i++) {
  itemsCopy[i] = items[i];
}
// Good
const itemsCopy = [...items];
```

🗣️ **함수명에는 동사를 넣는다**

```jsx
function: feature() (X)
function: get_feature() (O)
function: trainer() (X)
function: train() (O)
```

💫 **Module CSS 사용할 때, 작명법**

```jsx
import styles from './컴포넌트명.module.css';
```

</div>
</details>

:page_with_curl: 결과 화면
-

|화면|화면|화면|
|:---:|:---:|:---:|
|스크린샷|스크린샷|스크린샷|
