// css 초기화 설정
import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

const GlobalStyles = createGlobalStyle` 
  ${reset} 

  /* html element 초기화 */
  button {
    border:none; 
    box-shadow:none; 
    border-radius:0; 
    cursor:pointer
  }

  a {
    color: var(--black);
    text-decoration: none;
    outline: none
  }
  a:hover, a:active {
    color: initial;
    text-decoration: none;
  }

  /* 폰트 초기화 */
  body {
    font-family: var(--main-font);
    font-size: 14px;
    font-weight: 400;
    color: var(--fc-medium);
  }

  h1 {
    font-size: 28px;
    font-weight: 600;
    color: var(--fc-dark)
  }
  h2 {
    font-size: 21px;
  }

`;

export default GlobalStyles;
