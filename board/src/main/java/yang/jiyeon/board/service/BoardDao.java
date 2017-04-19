package yang.jiyeon.board.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao { // Data Access Object
    // �ۼ��� �޼���
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
    public int updateBoard(Board board) {
     
        return sqlSessionTemplate.update("yang.jiyeon.board.service.BoardMapper.updateBoard",board);
    }
    
    
    // �۹�ȣ�� ���н����带 �Է¹޾� �Ѱ��� �Խñ� ����
    public int deleteBoard(int boardNo, String boardPw) {
    	Board board = new Board();
    	board.setBoardNo(boardNo);
    	board.setBoardPw(boardPw);
        return sqlSessionTemplate.delete("yang.jiyeon.board.service.BoardMapper.deleteBoard",board);
    }
    
    // �Ѱ��� �Խñ� ���뺸��
    public Board getBoard(int boardNo) {
        return sqlSessionTemplate.selectOne("yang.jiyeon.board.service.BoardMapper.getBoard",boardNo);
    }
    
    // �Խñ� ���
    public List<Board> getBoardList(int currentPage, int pagePerRow) {  
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("beginRow", (currentPage-1)*pagePerRow);
    	map.put("pagePerRow", pagePerRow);
    	return sqlSessionTemplate.selectList("yang.jiyeon.board.service.BoardMapper.getBoardList",map);
    }
    
    // ��ü �� ī��Ʈ
    public int getBoardCount() {
        return sqlSessionTemplate.selectOne("yang.jiyeon.board.service.BoardMapper.getBoardCount");
    }
    
    // �۾��� �޼���
    public int insertBoard(Board board) {
        return sqlSessionTemplate.insert("yang.jiyeon.board.service.BoardMapper.insertBoard", board);
    }
    
  
}



