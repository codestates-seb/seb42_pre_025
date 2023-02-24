const getData = async (url, setState) => {
  try {
    const res = await fetch(url, {
      method: 'GET',
      mode: 'cors'
    });
    const data = await res.json();
    console.log(typeof data);
    console.log(data);
    setState(data.data);
  } catch (error) {
    console.error(error);
  }
};

const postData = async (url, newData) => {
  // ! 빈 문자열 들어올 때 처리해줘야 함
  // if (userNameValue === '' || emailValue === '') return;

  try {
    const res = await fetch(url, {
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

const deleteData = async (url, id) => {
  try {
    const res = await fetch(`${url}/${id}`, {
      method: 'DELETE',
      mode: 'cors'
    });
    // ! 새로고침 여부 확인
    window.location.reload();
    return await res.json();
  } catch (err) {
    console.log(err);
  }
};

export { getData, postData, deleteData };
