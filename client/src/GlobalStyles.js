// css 초기화 설정
import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

const GlobalStyles = createGlobalStyle` 
  ${reset} 

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
`;

export default GlobalStyles;
