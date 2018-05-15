<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.model1.board.dto.BoardRequest"%>
<%@ page import="com.model1.board.dao.BoardDao"%>
<%@ page import="com.model1.board.dto.BoardFile"%>
<%@ page import="java.util.Enumeration" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<%
	String uploadPath=request.getRealPath("upload");
	MultipartRequest multi;
	BoardFile boardFile = new BoardFile();
	int size = 10*1024*1024;
	int result;
	System.out.println("uploadPath : "+uploadPath);
	multi = new MultipartRequest(request, uploadPath, size, new DefaultFileRenamePolicy()); 
	BoardRequest boardRequest = new BoardRequest();
	boardRequest.setBoardTitle(multi.getParameter("boardTitle"));
	boardRequest.setBoardContent(multi.getParameter("boardContent"));
	boardRequest.setBoardPw(multi.getParameter("boardPw"));
	
	BoardDao boardDao = new BoardDao();
	result = (int) boardDao.insertBoard(boardRequest);
	if(result != 0){
		boardFile = boardDao.uploadFile(multi, result);
	}
	if(boardFile != null){
		boardDao.insertBoardFile(boardFile);
	}
	response.sendRedirect(request.getContextPath()+"/model1Board.jsp");
%>