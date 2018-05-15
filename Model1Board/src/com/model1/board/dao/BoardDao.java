package com.model1.board.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.model1.board.driver.DbConnection;
import com.model1.board.dto.BoardFile;
import com.model1.board.dto.BoardRequest;
import com.model1.board.dto.BoardResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardDao {
	//model1_board 게시글 리스트를 리턴해준다. (여기에서 페이징을 위한 값들역시 리턴해준다.많은 값들을 리턴하기 위해 Map을 이용한다.)
	public Map<String, Object> getBoardList(int currentPage, int pagePerRow){
		Map<String, Object> map = new HashMap<String, Object>();		
		List<BoardResponse> list = new ArrayList<BoardResponse>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT board_no AS boardNo, board_title AS boardTitle, board_date AS boardDate"
					+ " FROM model1_board"
					//auto increment이기때문에, 글번호가 가장 높은것이 1번페이지에 와야하므로 desc를 사용!
					+ " ORDER BY board_no DESC"
					//모든 글을 받아오는것이 아닌 특정 페이지의 글만 받아오면 되기때문에 limit를 사용하여 특정부분만 짜른다.
					+ " LIMIT ?, ?";
		//페이징을 위해서 row카운팅 해야함.
		int totalRow = getBoardTotalRow();
		// <<에 넣을 값
		int startPage = 1;
		// >>에 넣을값
		int lastPage = totalRow/pagePerRow;
		if(totalRow%pagePerRow != 0) {
			lastPage++;
		}
		// 현제페이지의 글 출력 범위를 설정해주기 위해!~ beginRow번째 부터  (pagePerRow)개의 글 가져오기
		int beginRow = (currentPage-1)*10;
		int beforePage = ((currentPage-1)/10)*10;
		int nextPage = beforePage + 11;
		try {
			connection = DbConnection.DbConn();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, beginRow);
			statement.setInt(2, pagePerRow);
			System.out.println(statement);
			result = statement.executeQuery();
			while(result.next()) {
				BoardResponse board = new BoardResponse();
				board.setBoardNo(result.getInt("boardNo"));
				board.setBoardTitle(result.getString("boardTitle"));
				board.setBoardDate(result.getString("boardDate"));
				list.add(board);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(result != null) try{ result.close(); } catch(SQLException e) { }
			if(statement != null) try{ statement.close(); } catch(SQLException e) { }
			if(connection != null) try{ statement.close(); } catch(SQLException e) { }
		}
		map.put("list", list);
		map.put("startPage", startPage);
		map.put("lastPage", lastPage);
		map.put("beforePage", beforePage);
		map.put("nextPage", nextPage);
		return map;
	}
	
	//model1_board 테이블의 특정 글 상세보기 기능을 위해 BoardResponse를 리턴해준다.
	public BoardResponse getBoardContent(int boardNo) {
		BoardResponse boardResponse = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT board_no AS boardNo, board_title AS boardTitle, board_content AS boardContent"
					+ ", board_date AS boardDate FROM model1_board WHERE board_no = ?";
		try {
			boardResponse = new BoardResponse();
			connection = DbConnection.DbConn();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, boardNo);
			result = statement.executeQuery();
			if(result != null) {
				result.next();
				boardResponse.setBoardNo(result.getInt("boardNo"));
				boardResponse.setBoardTitle(result.getString("boardTitle"));
				boardResponse.setBoardContent(result.getString("boardContent"));
				boardResponse.setBoardDate(result.getString("boardDate"));
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(result != null) try{ result.close(); } catch(SQLException e) { }
			if(statement != null) try{ statement.close(); } catch(SQLException e) { }
			if(connection != null) try{ statement.close(); } catch(SQLException e) { }
		}
		
		return boardResponse;
	}
	
	//model1_board 테이블의 총 ROW수를 리턴 해 준다.(게시판에서 라스트페이지 구할떄 총 ROW수가 필요)
	public int getBoardTotalRow() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		int totalRow = 0;
		String sql = "SELECT COUNT(*) AS totalRow FROM model1_board";
		try {
			connection = DbConnection.DbConn();
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery();
			if(result != null) {
				result.next();
				totalRow = result.getInt("totalRow");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(result != null) try{ result.close(); } catch(SQLException e) { }
			if(statement != null) try{ statement.close(); } catch(SQLException e) { }
			if(connection != null) try{ statement.close(); } catch(SQLException e) { }
		}
		
		return totalRow;
	}
	
	//model1_board 테이블에 게시글 추가.
	public long insertBoard(BoardRequest boardRequest) {
		System.out.println("---------------");
		System.out.println(boardRequest.toString());
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet result = null;
		long key = 0;
		String sql = "INSERT INTO model1_board (board_pw, board_title, board_content, board_date) "
					+ "values(?, ?, ?, now())";
		try {
			connection = DbConnection.DbConn();
			statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
			statement.setString(1, boardRequest.getBoardPw());
			statement.setString(2, boardRequest.getBoardTitle());
			statement.setString(3, boardRequest.getBoardContent());
			System.out.println(statement);
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			if (result != null && result.next()) {
			    key = result.getLong(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null) try{ statement.close(); } catch(SQLException e) { }
			if(connection != null) try{ statement.close(); } catch(SQLException e) { }
		}
		System.out.println("몇번쨰글이냐?"+key);
		return key;
	}
	
	//파일 업로드를함과 동시에, 파일정보를 가져와서 리턴해준다.
	public BoardFile uploadFile(MultipartRequest multi, int boardNo) {
		BoardFile boardFile = new BoardFile(); 
		try {
			Enumeration files = multi.getFileNames();
			String file = (String) files.nextElement();
			UUID uuid = UUID.randomUUID();
			String boardFileName = uuid.toString().replaceAll("-", "");
			String boardFileRealName = multi.getOriginalFileName(file);
			String boardFileType = multi.getContentType(file);
			String boardFileExt = multi.getOriginalFileName(file).substring(multi.getOriginalFileName(file).lastIndexOf(".")+1);
						
			boardFile.setBoardNo(boardNo);
			boardFile.setBoardFileName(boardFileName);
			boardFile.setBoardFileRealName(boardFileRealName);
			boardFile.setBoardFileType(boardFileType);
			boardFile.setBoardFileExt(boardFileExt);
			System.out.println(boardFile.toString());
		} catch (Exception e) {
			boardFile = null;
		}
		return boardFile;
	}
	
	public void insertBoardFile(BoardFile boardFile) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO model1_board_file (board_no, board_file_name, board_file_real_name, board_file_type, board_file_ext)"
					+ " VALUES(?, ?, ?, ?, ?)";
		try {
			connection = DbConnection.DbConn();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, boardFile.getBoardNo());
			statement.setString(2, boardFile.getBoardFileName());
			statement.setString(3, boardFile.getBoardFileRealName());
			statement.setString(4, boardFile.getBoardFileType());
			statement.setString(5, boardFile.getBoardFileExt());
			System.out.println("boardFile.statement : " + statement);
			statement.executeUpdate();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) try { connection.close(); } catch(SQLException e) {} 
			if(statement != null) try { statement.close(); } catch(SQLException e) {}
		}
	}
}
