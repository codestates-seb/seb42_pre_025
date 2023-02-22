import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

const StyledButton = styled.button`
  width: ${(props) => props.width || 'inherit'};
  padding: ${(props) => props.padding || '8px 10px'};
  background-color: ${(props) => props.bgColor || 'var(--button-primary-bg-color)'};
  color: ${(props) => props.color || 'var(--white)'};
  border-style: ${(props) => (props.bdColor ? 'solid' : 'none')};
  border-color: ${(props) => props.bdColor || 'none'};
  border-radius: 4px;
  font-size: 13px;
`;

function Button({ text, path = '', addStyle = {} }) {
  const navigate = useNavigate();
  const { backgroundColor, color, borderColor, padding, width } = addStyle;
  console.log(addStyle);
  console.log(borderColor);
  const goTo = (path) => {
    navigate(path);
  };

  return (
    <StyledButton
      width={width}
      padding={padding}
      bgColor={backgroundColor}
      bdColor={borderColor}
      color={color}
      onClick={path ? () => goTo(path) : null}
    >
      {text}
    </StyledButton>
  );
}

export default Button;
