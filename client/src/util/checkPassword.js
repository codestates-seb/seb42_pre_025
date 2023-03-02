export const checkPassword = (str) => {
  const regexp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
  const result = regexp.test(str);

  if (!result) {
    alert(
      'Passwords must contain at least eight characters, including at least 1 letter and 1 number.'
    );
  }
  return result;
};
