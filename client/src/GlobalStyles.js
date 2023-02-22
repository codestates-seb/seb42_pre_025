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
`;

export default GlobalStyles;
