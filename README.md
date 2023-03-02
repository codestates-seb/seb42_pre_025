# :headphones: 네? 뉴진스의 하입보이요 Team 입니다.

:bulb: 서비스 소개
-
stackoverflow clone 사이트 입니다.

:paperclip: 배포 주소
-
https://seb42-pre-025.vercel.app/

:calendar: 프로젝트 기간
-
23.02.13 ~ 23.03.02

:file_folder: 문서함
- 
+ [사용자 요구사항 정의서](https://docs.google.com/spreadsheets/d/1WHwyDLI1bqthNM4a_FT1rRkLSApj1W5WoN8jVh_OHOg/edit#gid=1661060620)
+ [API 명세서](https://docs.google.com/spreadsheets/d/1WHwyDLI1bqthNM4a_FT1rRkLSApj1W5WoN8jVh_OHOg/edit#gid=961764758)
+ [User Flow](https://www.figma.com/file/uNUbBMMRbvj1bIJcq6d0qG/User-Flow?node-id=0%3A1&t=XcrCYRrGDkqZgcME-0)
+ [개발자 테스트](https://docs.google.com/spreadsheets/d/1WHwyDLI1bqthNM4a_FT1rRkLSApj1W5WoN8jVh_OHOg/edit#gid=1232042637)


  
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


:pencil2: 커밋 컨벤션
-

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
    

- ****재할당이 불가능하면 `const`를, 재할당이 가능한 변수는`let`을 사용하여 선언한다.**
- ****`var`는 절대로 사용하지 않도록 한다.**
- **변수를 한꺼번에 여러 개 선언해야 할 경우** ****`const` 를 `let`보다 먼저 선언한다.**
- ⚠️ ****전역 변수를 사용하지 않는다.****
- ****변수 등을 조합해서 문자열을 생성하는 경우 템플릿 문자열(backtick ````)을 이용한다.**
- ****함수는  화살표 함수로 작성한다.****

❌  **변수, 클래스명에는 동사를 넣지 않는다**

```jsx
class: FeatureExtract (X)
class: FeatureExtractor (O)

const: work (X)
const: worker (O)
```

❌ **변수명에 굳이 관사를 넣지 않는다.**

```jsx
const: a_cat (X)
const: cat (O)
```

❌ **변수명에 전치사는 최대한 생략한다.**

```jsx
const : the_number_of_worker (X)
const : worker_num (O)
```

❌ ****배열 복사 시 순환문을 사용하지 않는다.****

복잡한 객체를 복사할 때 **`전개 연산자`**를 사용하면 좀 더 명확하게 정의할 수 있고 가독성이 좋아진다.

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

🗣️ **함수명에는 동사를 넣는다**

```jsx
function: feature() (X)
function: get_feature() (O)

function: trainer() (X)
function: train() (O)
```

💫 **Module CSS 사용할 때, 작명법**

```jsx
import styles from './컴포넌트명.module.css';
```

</div>
</details>
:page_with_curl: 결과 화면
-

Java Spring info
-
- Language : Java
- Spring Boot ver : 2.7.8
- Project : Metadata
- Group : com.stackoverflow
- Artifact : team25
- Name : team25
- Description : seb42_pre_team25
- Package name :  com.stackoverflow.team25

Packaging
.Jar

Java ver.
11

dependencies
- Lombok
- Spring Configuration Processor
- Spring Web
- Spring Security
- OAuth2 Client
- Spring Data JPA
- PostgreSQL Driver
- H2 Database
- Validation
- Java Mail Sender
- Spring REST Docs

추가 dependancies
- Gson
- MapStruct
