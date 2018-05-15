<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.model1.board.dto.BoardResponse"%>
<%@page import="com.model1.board.dao.BoardDao"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>model1BoardContent</title>
</head>
<body>
<%
int boardNo = Integer.parseInt(request.getParameter("boardNo"));
BoardDao boardDao = new BoardDao();
BoardResponse boardResponse = boardDao.getBoardContent(boardNo);
%>
<h1>model1_board_content</h1>
<table border="1">
	<thead>
		<tr>
			<th>글제목</th>
			<th><%= boardResponse.getBoardTitle() %></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>글내용</td>
			<td><%= boardResponse.getBoardContent() %></td>
		</tr>
	</tbody>
</table>
<input type="button" value="목록" onclick="getBoardList()">
<script>
	function getBoardList(){
		location.href="<%= request.getContextPath()%>/model1Board.jsp";
	}
</script>
</body>
</html>