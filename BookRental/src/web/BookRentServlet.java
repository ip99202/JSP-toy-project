package web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class BookRentServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String method = request.getMethod();
		String id = request.getParameter("ID");
		int code = Integer.parseInt(id);

		if (rentBook(code) == 1)
			response.sendRedirect("/BookRental/RentResult.jsp?RESULT=fail");

		else
			response.sendRedirect("/BookRental/RentResult.jsp?RESULT=success");
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
			ResultSet rs = stmt.executeQuery("select rent from booksinfo where code=" + code + ";");
			if (rs.next()) {
				int isRent = rs.getInt("rent");
				if (isRent == 1)
					return 1;

				int rr = stmt.executeUpdate("update booksinfo set count = count+1, rent = 1 where code = " + code + ";");
			}
			else
				return 1;
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