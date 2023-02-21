import { useEffect, useState } from 'react';

const { REACT_APP_BASE_URL } = process.env;
const URL = `${REACT_APP_BASE_URL}/api/users`;
console.log(URL);

function Input() {
  const [userNameValue, setUserNameValue] = useState('');
  const [emailValue, setEmailValue] = useState('');

  const handleUserNameValue = (e) => {
    setUserNameValue(e.target.value);
  };
  const handleEmailValue = (e) => {
    setEmailValue(e.target.value);
  };

  const postData = async () => {
    if (userNameValue === '' || emailValue === '') return;

    const newData = {
      displayName: userNameValue,
      email: emailValue
    };

    try {
      const res = await fetch(URL, {
        method: 'POST',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(newData)
      });
      return await res.json();
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <form style={{ marginBottom: '8px' }}>
      <label htmlFor='userName'>Display Name</label>
      <input
        id='userName'
        type='text'
        placeholder='이름을 입력하세요'
        value={userNameValue}
        onChange={handleUserNameValue}
      ></input>
      <br></br>
      <label htmlFor='email'>Email</label>
      <input
        id='email'
        type='email'
        placeholder='이메일을 입력하세요'
        value={emailValue}
        onChange={handleEmailValue}
      ></input>
      <br></br>
      <button onClick={postData}>제출</button>
    </form>
  );
}

function API_TEST() {
  const [userInfo, setUserInfo] = useState({});

  useEffect(() => {
    const getData = async () => {
      try {
        const res = await fetch(`${URL}/1`);
        const data = await res.json();
        // console.log(data);
        // console.log(typeof data);
        setUserInfo(data.data);
      } catch (error) {
        console.error(error);
      }
    };
    getData();
  }, []);

  console.log(userInfo);

  const deleteData = async (id) => {
    try {
      const res = await fetch(`${URL}/${id}`, {
        method: 'DELETE',
        mode: 'cors'
      });
      window.location.reload();
      return await res.json();
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <>
      <Input />
      <hr></hr>
      <ul>
        <li>
          <p>이름: {userInfo && userInfo.displayName}</p>
          <p>이메일: {userInfo && userInfo.email}</p>
          <button style={{ marginLeft: '4px' }} onClick={() => deleteData(userInfo.userId)}>
            삭제
          </button>
        </li>
      </ul>
    </>
  );
}

export default API_TEST;
