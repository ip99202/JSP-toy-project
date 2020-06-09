package web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class BookAddServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String method = request.getMethod();
		String id = request.getParameter("ID");
		String title = request.getParameter("TITLE");
		String writer = request.getParameter("WRITER");
		String s_price = request.getParameter("PRICE");
		
		
		
		int res;
		res = rentBook(id, title, writer, s_price);
		if(res == 0)
			response.sendRedirect("/BookRental/WebTemplate.jsp?BODY_PATH=AddResult.jsp?RESULT=success");
		else if(res == 1)
			response.sendRedirect("/BookRental/WebTemplate.jsp?BODY_PATH=AddResult.jsp?RESULT=exist");
		else if(res == 2)
			response.sendRedirect("/BookRental/WebTemplate.jsp?BODY_PATH=AddResult.jsp?RESULT=empty");
		else if(res == 3)
			response.sendRedirect("/BookRental/WebTemplate.jsp?BODY_PATH=AddResult.jsp?RESULT=error");

	}

	private int rentBook(String id, String title, String writer, String s_price) throws ServletException {
		Connection conn = null;
		Statement stmt = null;
		if("".equals(id) || "".equals(title) || "".equals(writer) || "".equals(s_price))
			return 2;
		
		if(!isNumber(id) || !isNumber(s_price)) 
			return 3;
		
		int code = Integer.parseInt(id);
		int price = Integer.parseInt(s_price);
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
				int rr = stmt.executeUpdate("insert into booksinfo (code, title, writer, price, rent, count) "
						+ "values (" + code + ", '" + title + "', '" + writer + "', " + price + ", " + 0 + ", " + 0 + ");");
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
	
	public static boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}