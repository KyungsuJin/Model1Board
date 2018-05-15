<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>model1BoardWriteForm</title>
</head>
<body>
	<form action="<%= request.getContextPath() %>/model1BoardWritePro.jsp" method="post" enctype="multipart/form-data">
		<table border="1">
			<thead>
				<tr>
					<th width="10%">글제목</th>
					<th><input type="text" name="boardTitle" placeholder="글제목"></th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td width="10%">글내용</td>
					<td><textarea name="boardContent" rows="30" cols="30"></textarea></td>
				</tr>
			</tbody>
			
			<tfoot>
				<tr>
					<td width="10%">비밀번호</td>
					<td><input type="password" name="boardPw"></td>
				</tr>
				
				<tr>
					<td width="10%">파일</td>
					<td><input type="file" name="multipartFile"></td>
				</tr>
			</tfoot>
		</table>
		<input type="submit" value="작성">
	</form>
</body>
</html>