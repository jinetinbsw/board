package kr.or.ksmart.service;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public int updateBoard(Board board) {
		return sqlSessionTemplate.update("kr.or.ksmart.service.BoardMapper.updateBoard",board);
        /*Connection connection = null;
        PreparedStatement statement = null;
        int row = 0;
        try {
            connection = this.getConnection();
            String sql = "UPDATE board SET board_title=?, board_content=? WHERE board_no=? AND board_pw=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, board.getBoardTitle());
            statement.setString(2, board.getBoardContent());
            statement.setInt(3, board.getBoardNo());
            statement.setString(4, board.getBoardPw());
            row = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("예외 발생");
        } finally {
            try {statement.close();} catch(Exception e){}
            try {connection.close();} catch(Exception e){}
        } */
//        return row;
    }
    
    
    // 글번호와 글패스워드를 입력받아 한개의 게시글 삭제
    public int deleteBoard(int boardNo, String boardPw) {
    	Board board = new Board();
    	board.setBoardNo(boardNo);
    	board.setBoardPw(boardPw);
    	return sqlSessionTemplate.delete("kr.or.ksmart.service.BoardMapper.deleteBoard",board);
       /* Connection connection = null;
        PreparedStatement statement = null;
        int row = 0;
        try {
            connection = this.getConnection();
            String sql = "DELETE FROM board WHERE board_no=? AND board_pw=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, boardNo);
            statement.setString(2, boardPw);
            row = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("예외 발생");
        } finally {
            try {statement.close();} catch(Exception e){}
            try {connection.close();} catch(Exception e){}
        } */
       /* return row;*/
    }
    
    // 한개의 게시글 내용보기
    public Board getBoard(int boardNo) {
    	return sqlSessionTemplate.selectOne("kr.or.ksmart.service.BoardMapper.getBoard", boardNo);
        /*Board board = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            String sql = "SELECT board_title, board_content, board_user, board_date FROM board WHERE board_no=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, boardNo);
            rs = statement.executeQuery();
            if(rs.next()) {
                board = new Board();
                board.setBoardNo(boardNo);
                board.setBoardTitle(rs.getString("board_title"));
                board.setBoardContent(rs.getString("board_content"));
                board.setBoardUser(rs.getString("board_user"));
                board.setBoardDate(rs.getString("board_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("예외 발생");
        } finally {
            try {rs.close();} catch(Exception e){}
            try {statement.close();} catch(Exception e){}
            try {connection.close();} catch(Exception e){}
        }   */
//        return board;
    }
    
    // 게시글 목록
    public List<Board> getBoardList(int currentPage, int pagePerRow){
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("beginRow", (currentPage-1)*pagePerRow);
    	map.put("pagePerRow", pagePerRow);
    	return sqlSessionTemplate.selectList("kr.or.ksmart.service.BoardMapper.getBoardList",map);
    }
    /*public ArrayList<Board> getBoardList(int currentPage, int pagePerRow) {
        ArrayList<Board> list = new ArrayList<Board>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            String listSql = "SELECT board_no, board_title, board_user, board_date FROM board ORDER BY board_date DESC LIMIT ?, ?";
            statement = connection.prepareStatement(listSql);
            statement.setInt(1, (currentPage-1)*pagePerRow); 
            statement.setInt(2, pagePerRow); 
            rs = statement.executeQuery();
            while(rs.next()) {
                Board board = new Board();
                board.setBoardNo(rs.getInt("board_no"));
                board.setBoardTitle(rs.getString("board_title"));
                board.setBoardUser(rs.getString("board_user"));
                board.setBoardDate(rs.getString("board_date"));
                list.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("예외 발생");
        } finally {
            try {rs.close();} catch(Exception e){}
            try {statement.close();} catch(Exception e){}
            try {connection.close();} catch(Exception e){}
        }
        return list;
    }*/
    
    // 전체 글 카운트
    public int getBoardCount() {
    	
      /*  int count = 0; 
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            String totalSql = "SELECT COUNT(*) FROM board"; // board테이블의 전체행의 수를 반환
            statement = connection.prepareStatement(totalSql);
            rs = statement.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("예외 발생");
        } finally {
            try {rs.close();} catch(Exception e){}
            try {statement.close();} catch(Exception e){}
            try {connection.close();} catch(Exception e){}
        }*/
        return sqlSessionTemplate.selectOne("kr.or.ksmart.service.BoardMapper.getBoard");
    }
    
    // 글쓰기 메서드
    public int insertBoard(Board board) {
    	return sqlSessionTemplate.insert("kr.or.ksmart.service.BoardMapper.insertBoard", board);
        /*Connection connection = null;
        PreparedStatement statement = null;
        int row = 0;
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO board(board_pw, board_title, board_content, board_user, board_date) values(?,?,?,?,now())";
            statement = connection.prepareStatement(sql);
            statement.setString(1,board.getBoardPw());
            statement.setString(2,board.getBoardTitle());
            statement.setString(3,board.getBoardContent());
            statement.setString(4,board.getBoardUser());
            row = statement.executeUpdate(); // insert 쿼리를 실행
        } catch(Exception e) {
            e.printStackTrace();
            System.out.print("예외 발생");
        } finally {
            try {statement.close();} catch(Exception e){}
            try {connection.close();} catch(Exception e){}
        }
        return row;*/
    }
    
    // 메서드 마다 드라이브 로딩과 Connection을 구하는 코드가 중복되어 하나의 메서드로 추출하였다
   /* public Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/devboard23db?useUnicode=true&characterEncoding=euckr";
        String dbUser = "devboard23id";
        String dbPw = "devboard23pw";
        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPw);
        return connection;
    }*/
}
