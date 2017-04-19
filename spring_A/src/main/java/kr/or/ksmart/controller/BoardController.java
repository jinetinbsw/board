package kr.or.ksmart.controller;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart.service.Board;
import kr.or.ksmart.service.BoardDao;

@Controller
public class BoardController {
	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping(value="/boardModify", method = RequestMethod.GET)
	public String boardModify(Model model,@RequestParam(value="boardNo", required=true) int boardNo){
		Board board = boardDao.getBoard(boardNo);
		model.addAttribute("board",board);
		return "boardModify";
	}
	
	@RequestMapping(value="/boardModify", method = RequestMethod.POST)
	public String boardModify(Board board){
		boardDao.updateBoard(board);
		return "redirect:/boardView?boardNo="+board.getBoardNo();
	}
	
	@RequestMapping(value="/boardRemove", method = RequestMethod.GET)
	public String boardRemove(@RequestParam(value="boardNo", required=true) int boardNo){
		return "boardRemove";
	}
	
	@RequestMapping(value="/boardRemove", method = RequestMethod.POST)
	public String boardRemove(@RequestParam(value="boardNo") int boardNo,@RequestParam(value="boardPw") String boardPw){
		boardDao.deleteBoard(boardNo, boardPw);
		return "redirect:/boardList";
	}
	
	@RequestMapping(value="/boardView", method = RequestMethod.GET)
	public String boardView(Model model,@RequestParam(value="boardNo") int boardNo){
		Board board = boardDao.getBoard(boardNo);
		model.addAttribute("board",board);
		return "/boardView";
	}
	
	@RequestMapping(value={"/","/boardList"}, method = RequestMethod.GET)
	public String boardList(Model model,@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage){
		int boardCount = boardDao.getBoardCount();
		int pagePerRow = 10;
		int lastPage = (int)(Math.ceil(boardCount / pagePerRow));
		List<Board> list = boardDao.getBoardList(currentPage, pagePerRow);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("boardCount",boardCount);
		model.addAttribute("lastPage",lastPage);
		model.addAttribute("list",list);
		return "/boardList";
	}
	
	@RequestMapping(value="/boardAdd", method = RequestMethod.GET)
	public String boardAdd(){
		System.out.println("boardAdd GET 실행되었음?");		// 단위 테스트
		return "boardAdd";
	}
	
	@RequestMapping(value="/boardAdd", method = RequestMethod.POST)
	public String boardAdd(Board board){
		System.out.println(board);		// 단위 테스트
		boardDao.insertBoard(board);
		return "redirect:/boardList";
	}
}
