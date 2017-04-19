package yang.jiyeon.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import yang.jiyeon.board.service.Board;
import yang.jiyeon.board.service.BoardDao;
@Controller

public class BoardController {
	@Autowired
	private BoardDao boardDao;
	
	// �� ���� �� ��û
	@RequestMapping(value = "/boardModify", method = RequestMethod.GET)
	public String boardModify(Model model
							  , @RequestParam(value="boardNo", required=true) int boardNo) {
		Board board = boardDao.getBoard(boardNo);
		model.addAttribute("board", board);
		return "/boardModify";
		
	}
	// �� ���� ��û
	@RequestMapping(value = "/boardModify", method = RequestMethod.POST)
	public String boardModify(Board board) {
		boardDao.updateBoard(board);
		return "redirect:/boardView?boardNo="+board.getBoardNo();
	}
	
	// �� ���� �� ��û(��й�ȣ �Է� ��)
	@RequestMapping(value = "/boardRemove", method = RequestMethod.GET)
	public String boardRemove(@RequestParam(value="boardNo", required=true) int boardNo) {
		return "/boardRemove";
		
	}
	// �� ���� ��û
	@RequestMapping(value = "/boardRemove", method = RequestMethod.POST)
	public String boardRemove(@RequestParam(value="boardNo", required=true) int boardNo
							, @RequestParam(value="boardPw", required=true) String boardPw) {
		boardDao.deleteBoard(boardNo, boardPw);
		return "redirect:/boardList";
		
	}
	
	// �� �� ���� ��û
	@RequestMapping(value = "/boardView", method = RequestMethod.GET)
	public String boardView(Model model
							, @RequestParam(value="boardNo",required=true) int boardNo) {
		Board board = boardDao.getBoard(boardNo);
		model.addAttribute("board", board);
		return "/boardView";
	}
	
	
	// ����Ʈ ��û
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardList(Model model, @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		int boardCount = boardDao.getBoardCount();
		int pagePerRow = 10;
		int lastPage = (int)(Math.ceil(boardCount/pagePerRow));
		List<Board> list = boardDao.getBoardList(currentPage, pagePerRow);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("pagePerRow", pagePerRow);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("list", list);
		
		return "/boardList";
	}
	// �Է� ������ ��û
	@RequestMapping(value = "/boardAdd", method = RequestMethod.GET)
	public String boardAdd(){
		System.out.println("boardAdd �� ��û");
		return "/boardAdd";
	}
	// �Է�(�׼�)��û
	@RequestMapping(value = "/boardAdd", method = RequestMethod.POST)
	public String boardAdd(Board board){
		boardDao.insertBoard(board);
		System.out.println(board);
		return "redirect:/boardList";
	}

}
