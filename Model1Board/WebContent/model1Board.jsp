<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model1.board.dao.BoardDao" %>
<%@ page import="com.model1.board.dto.BoardResponse" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>model1Board</title>
<style type="text/css">
	#writeBoard{}
</style>
</head>
<body>
	<%
	BoardDao boardDao = new BoardDao();
	int currentPage;
	if(request.getParameter("currentPage") == null){
		currentPage = 1;
	} else {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int pagePerRow = 10;
	Map<String, Object> map = boardDao.getBoardList(currentPage, pagePerRow);
	List<BoardResponse> list = (List<BoardResponse>) map.get("list");
	int startPage = (int) map.get("startPage");
	int lastPage = (int) map.get("lastPage");
	int beforePage = (int) map.get("beforePage");
	int nextPage = (int) map.get("nextPage");
	%>
	<h1> model1_board </h1>
	<input type="button" id="writeBoard" value="글쓰기" onclick="writeBoard()">
	<table border="1">
		<thead>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<%
			if(list != null){
				for(BoardResponse board : list){
					System.out.println("test"+board.toString());
				%>
					<tr>
						<td><%= board.getBoardNo() %></td>
						<td><a href="<%= request.getContextPath() %>/model1BoardContent.jsp?boardNo=<%= board.getBoardNo() %>"><%= board.getBoardTitle() %></a></td>
						<td><%= board.getBoardDate() %></td>
					</tr>
				<%
				}
			}
			%>
		</tbody>
	</table>
	
	<%
	if(beforePage == 0){ }
	else{
	%>
		<a href="<%= request.getContextPath() %>/model1Board.jsp?currentPage=<%= startPage %>"><<</a>
		<a href="<%= request.getContextPath() %>/model1Board.jsp?currentPage=<%= beforePage %>"><</a>
	<%
	}
	for(int i = beforePage + 1 ; i < nextPage ; i++){
		if(i == currentPage && (i == lastPage || lastPage == 0)){
	%>
			<%= i %>
		<%
			break;
		} else if(i == currentPage){
		%>
			<%= i %>
		<%
		} else if(i == lastPage){
		%>
			<a href="<%= request.getContextPath() %>/model1Board.jsp?currentPage=<%= i %>"><%= i %></a>
		<%
			break;
		} else{
		%>
			<a href="<%= request.getContextPath() %>/model1Board.jsp?currentPage=<%= i %>"><%= i %></a>
		<%
		}
	}	
	if(lastPage < nextPage){ }
	else{
	%>
		<a href="<%= request.getContextPath() %>/model1Board.jsp?currentPage=<%= nextPage %>">></a>
		<a href="<%= request.getContextPath() %>/model1Board.jsp?currentPage=<%= lastPage %>">>></a>
	<%
	}
	%>
	
	<script>
	function writeBoard(){
		location.href="<%= request.getContextPath()%>/model1BoardWriteForm.jsp";
	}
	</script>
</body>
</html>