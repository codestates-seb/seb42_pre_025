import { useEffect, useState } from 'react';

const BASE_URL = 'http://ec2-15-165-216-129.ap-northeast-2.compute.amazonaws.com:8080';

function Input() {
  const [inputValue, setInputValue] = useState('');

  const handleInput = (e) => {
    setInputValue(e.target.value);
  };

  const postData = async () => {
    if (inputValue === '') return;

    const newData = {
      text: inputValue
    };

    try {
      const res = await fetch(BASE_URL, {
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
      <input
        type='text'
        placeholder='텍스트를 입력하세요'
        value={inputValue}
        onChange={handleInput}
      ></input>
      <button onClick={postData}>제출</button>
    </form>
  );
}

function API_TEST() {
  const [data, setData] = useState([]);

  useEffect(() => {
    const getData = async () => {
      try {
        const res = await fetch(BASE_URL);
        const data = await res.json();
        // console.log(data);
        setData(data);
      } catch (error) {
        console.error(error);
      }
    };
    getData();
  }, []);
  console.log(data);

  const deleteData = async (id) => {
    try {
      const res = await fetch(`${BASE_URL}/${id}`, {
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
      <ul>
        {data.map((obj) => (
          <li key={obj.id}>
            <span>{obj.text}</span>
            <button style={{ marginLeft: '4px' }} onClick={() => deleteData(obj.id)}>
              삭제
            </button>
          </li>
        ))}
      </ul>
    </>
  );
}

export default API_TEST;
