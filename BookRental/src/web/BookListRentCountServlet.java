package web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class BookListRentCountServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String strFirstSeqNo = request.getParameter("FIRST_CODE");
		String strLastSeqNo = request.getParameter("LAST_CODE");
		BookList list;
		if (strFirstSeqNo != null) {
			list = readPrevPage(Integer.parseInt(strFirstSeqNo));
		} else if (strLastSeqNo != null) {
			list = readNextPage(Integer.parseInt(strLastSeqNo));
		} else {
			list = readNextPage(Integer.MAX_VALUE);
			list.setFirstPage(true);
		}
		
		request.setAttribute("BOOK_LIST", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BookListView.jsp");
		dispatcher.forward(request, response);
	}
	
	

	private BookList readPrevPage(int lowerCode) throws ServletException {
		BookList list = new BookList();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root", "1234567890");
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from booksinfo where code > " + lowerCode + " order by code asc;");
			for (int cnt = 0; cnt < 5; cnt++) {
				if (!rs.next())
					break;
				list.setCode(0, rs.getInt("code"));
				list.setTitle(0, rs.getString("title"));
				list.setWriter(0, rs.getString("writer"));
				list.setPrice(0, rs.getInt("price"));
				list.setRent(0, rs.getInt("rent"));
				list.setCount(0, rs.getInt("count"));
			}
			if (!rs.next())
				list.setFirstPage(true);
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
		return list;
	}
	
	
	private BookList readNextPage(int upperCode) throws ServletException {
		BookList list = new BookList();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root", "1234567890");
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("select * from booksinfo where code < " + upperCode + " order by code desc;");
			for (int cnt = 0; cnt < 5; cnt++) {
				if (!rs.next())
					break;
				list.setCode(cnt, rs.getInt("code"));
				list.setTitle(cnt, rs.getString("title"));
				list.setWriter(cnt, rs.getString("writer"));
				list.setPrice(cnt, rs.getInt("price"));
				list.setRent(cnt, rs.getInt("rent"));
				list.setCount(cnt, rs.getInt("count"));
			}
			if (!rs.next())
				list.setLastPage(true);
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
		return list;
	}
	
}