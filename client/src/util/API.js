// const getFetch = async (url, setState1, setState2, key, key2) => {
const getFetch = async (url) => {
  try {
    const res = await fetch(url, {
      method: 'GET'
      // mode: 'cors'
      //   headers: {
      //     Accept: 'application/json'
      //   }
    });

    return await res.json();
  } catch (error) {
    console.error(error);
  }
};

const postFetch = async (url, newData, jwt) => {
  try {
    const res = await fetch(url, {
      method: 'POST',
      // mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
        Authorization: jwt
      },
      body: JSON.stringify(newData)
    });

    if (res.ok) {
      return res;
    }
    // return await res.json();
    // console.log(...res.headers);
    // return res.headers.get('Location');
  } catch (err) {
    console.log(err);
  }
};

const deleteFetch = async (url) => {
  try {
    const res = await fetch(url, {
      method: 'DELETE'
      // mode: 'cors'
    });
    // ! 새로고침 여부 확인
    // window.location.reload();
    // return await res.json();
    return res;
  } catch (err) {
    console.log(err);
  }
};

export { getFetch, postFetch, deleteFetch };
