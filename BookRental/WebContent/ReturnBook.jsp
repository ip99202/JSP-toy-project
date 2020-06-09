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
	<h3>반납 목록</h3>
	<table border="1">
		<tr>
			<td width=60><center>ID</center></td>
			<td width=200><center>이름</center></td>
			<td width=180><center>저자</center></td>
			<td width=90><center>가격</center></td>
			<td width=80><center>대여 가능</center></td>
			<td width=80><center>대여 횟수</center></td>
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
					if (rs.getString("rent") == "1")
						isRent = "Y";
					else
						isRent = "N";
		%>
		<tr>
			<td><center><%=rs.getString("code")%></center></td>
			<td><center><%=rs.getString("title")%></center></td>
			<td><center><%=rs.getString("writer")%></center></td>
			<td><center><%=rs.getString("price")%></center></td>
			<td><center><%=isRent%></center></td>
			<td><center><%=rs.getString("count")%></center></td>
			<td><center>
					<a href="/BookRental/book-return?ID=<%=rs.getString("code")%>">반납</a>
				</center></td>
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