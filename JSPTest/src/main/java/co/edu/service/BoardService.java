package co.edu.service;

import java.util.List;

import co.edu.dao.BoardDAO;
import co.edu.vo.BoardVO;
import co.edu.vo.Criteria;

public class BoardService {
	private static BoardService instance = new BoardService();

	BoardDAO dao = new BoardDAO();
	
	private BoardService() {}
	
	public static BoardService getInstance() {
		return instance;
	}
	//게시글 등록
	public void addBoard(BoardVO vo) {
		dao.addBoard(vo);
	}
	
	//게시글 리스트 출력
	public List<BoardVO> BoardList(){
		return dao.getList();
	}
	
	//페이징
	public List<BoardVO> getListPageging(Criteria cri){
		return dao.getListPaging(cri); //10건씩		
	}
}
