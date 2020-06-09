package web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class BookReturnServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String method = request.getMethod();
		String id = request.getParameter("ID");
		int code = Integer.parseInt(id);

		rentBook(code);
		response.sendRedirect("/BookRental/WebTemplate.jsp?BODY_PATH=book-list?PAGE_NO=1");

	}

	private int rentBook(int code) throws ServletException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root",
					"1234567890");
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate("update booksinfo set rent = 0 where code = " + code + ";");

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
		return 0;
	}
}