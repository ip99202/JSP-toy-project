package web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class BookAddServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String method = request.getMethod();
		String id = request.getParameter("ID");
		int code = Integer.parseInt(id);
		String title = request.getParameter("TITLE");
		String writer = request.getParameter("WRITER");
		String s_price = request.getParameter("PRICE");
		int price = Integer.parseInt(s_price);

		if (rentBook(code, title, writer, price) == 1)
			response.sendRedirect("/BookRental/RentResult.jsp?RESULT=fail");

		else
			response.sendRedirect("/BookRental/RentResult.jsp?RESULT=success");
	}

	private int rentBook(int code, String title, String writer, int price) throws ServletException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root", "1234567890");
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select code from booksinfo where code=" + code + ";");
			if (rs.next()) 
				return 1;
			else {
				ResultSet rr = stmt.executeQuery("insert into booksinfo values (" + code + ", '" + title + "', " + price + ", " + 0 + ", " + 0 + ");");
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
		return 0;
	}
}