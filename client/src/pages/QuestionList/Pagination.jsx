import Paging from 'react-js-pagination';
import './Pagination.css';

function Pagination({ currentPage, setCurrentPage, totalElements }) {
  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  return (
    <Paging
      activePage={currentPage} // 현재 페이지
      itemsCountPerPage={10} // 한 페이지랑 보여줄 아이템 갯수
      totalItemsCount={totalElements} // 총 아이템 갯수
      pageRangeDisplayed={5} // paginator의 페이지 범위
      prevPageText={'‹'} // "이전"을 나타낼 텍스트
      nextPageText={'›'} // "다음"을 나타낼 텍스트
      onChange={handlePageChange} // 페이지 변경을 핸들링하는 함수
    />
  );
}

export default Pagination;
