package co.edu.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.service.BoardService;
import co.edu.vo.BoardVO;
import co.edu.vo.Criteria;
import co.edu.vo.Page;

public class BoardListPagingControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//페이징 처 된 리스트 출력
		// 서비스 -> DAO구현
		
		String pageNum = req.getParameter("pageNum");
		String amount = req.getParameter("amount");
		
		System.out.println(pageNum);
		System.out.println(amount);
		
		Criteria cri = new Criteria(); ///1, 10
		cri.setPageNum(Integer.parseInt(pageNum)); // 보고싶은 페이지
		cri.setAmount(Integer.parseInt(amount)); //페이지에 데이터 수
		
		
		BoardService service = BoardService.getInstance();
		List<BoardVO> pageList = service.getListPageging(cri);
	
		req.setAttribute("list", pageList);
		
		List<BoardVO> totalList = service.BoardList();
		int total = totalList.size();
		req.setAttribute("pageInfo", new Page(cri, total)); //현재페이지, 시작, 마지막페이지
		
		HttpUtil.forward(req, resp, "board/boardList.tiles");
		
	}

}
