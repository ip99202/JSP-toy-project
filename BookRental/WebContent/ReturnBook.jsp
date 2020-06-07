<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>

<html>
<head>
<title>반납 목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<td width=40>ID</td>
			<td width=200>이름</td>
			<td width=80>저자</td>
			<td width=90>가격</td>
			<td width=70>대여 가능</td>
			<td width=70>대여 횟수</td>
		</tr>

		<%
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			Statement stmt = null;

			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root",
						"1234567890");
				if (conn == null)
					throw new Exception("데이터베이스에 연결할 수 없습니다.");
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from booksinfo where rent=1 order by code asc;");

				while (rs.next()) {
					String isRent;
					if(rs.getString("rent") == "1")
						isRent = "Y";
					else
						isRent = "N";
		%>
		<tr>
			<td><%=rs.getString("code")%></td>
			<td><%=rs.getString("title")%></td>
			<td><%=rs.getString("writer")%></td>
			<td><%=rs.getString("price")%></td>
			<td><%=isRent%></td>
			<td><%=rs.getString("count")%></td>
			<td><a href="/BookRental/book-return?ID=<%=rs.getString("code")%>">반납</a></td>
		</tr>
		<%
			}
			} catch (Exception e) {
				throw new ServletException(e);
			} finally {
				try {
					stmt.close();
				} catch (Exception ignored) {
				}
				try {
					conn.close();
				} catch (Exception ignored) {
				}
			}
		%>
	</table>
</body>
</html>