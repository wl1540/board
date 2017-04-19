package yang.jiyeon.board.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao { // Data Access Object
    // 글수정 메서드
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
    public int updateBoard(Board board) {
     
        return sqlSessionTemplate.update("yang.jiyeon.board.service.BoardMapper.updateBoard",board);
    }
    
    
    // 글번호와 글패스워드를 입력받아 한개의 게시글 삭제
    public int deleteBoard(int boardNo, String boardPw) {
    	Board board = new Board();
    	board.setBoardNo(boardNo);
    	board.setBoardPw(boardPw);
        return sqlSessionTemplate.delete("yang.jiyeon.board.service.BoardMapper.deleteBoard",board);
    }
    
    // 한개의 게시글 내용보기
    public Board getBoard(int boardNo) {
        return sqlSessionTemplate.selectOne("yang.jiyeon.board.service.BoardMapper.getBoard",boardNo);
    }
    
    // 게시글 목록
    public List<Board> getBoardList(int currentPage, int pagePerRow) {  
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("beginRow", (currentPage-1)*pagePerRow);
    	map.put("pagePerRow", pagePerRow);
    	return sqlSessionTemplate.selectList("yang.jiyeon.board.service.BoardMapper.getBoardList",map);
    }
    
    // 전체 글 카운트
    public int getBoardCount() {
        return sqlSessionTemplate.selectOne("yang.jiyeon.board.service.BoardMapper.getBoardCount");
    }
    
    // 글쓰기 메서드
    public int insertBoard(Board board) {
        return sqlSessionTemplate.insert("yang.jiyeon.board.service.BoardMapper.insertBoard", board);
    }
    
  
}



