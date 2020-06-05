package web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class BookListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//		String strFirstSeqNo = request.getParameter("FIRST_CODE");
//		String strLastSeqNo = request.getParameter("LAST_CODE");
		String strPageNo = request.getParameter("PAGE_NO");
		BookList list;
//		if (strFirstSeqNo != null) {
//			list = readPrevPage(Integer.parseInt(strFirstSeqNo));
//		} else if (strLastSeqNo != null) {
//			list = readNextPage(Integer.parseInt(strLastSeqNo));
//		} else if (strPageNo != null) {
//			list = readPage(Integer.parseInt(strPageNo));
//		} else {
//			list = readNextPage(Integer.MAX_VALUE);
//			list.setFirstPage(true);
//		}
		if(strPageNo == null)
			strPageNo = "1";
		list = readPage(Integer.parseInt(strPageNo));
		list.setPageNum(readPageNum());
		
		request.setAttribute("BOOK_LIST", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BookListView.jsp");
		dispatcher.forward(request, response);
	}
	
	
//
//	private BookList readPrevPage(int lowerCode) throws ServletException {
//		BookList list = new BookList();
//		Connection conn = null;
//		Statement stmt = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root", "1234567890");
//			if (conn == null)
//				throw new Exception("데이터베이스에 연결할 수 없습니다.");
//			stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from booksinfo where code > " + lowerCode + " order by code asc limit 6;");
//			for (int cnt = 0; cnt < 5; cnt++) {
//				if (!rs.next())
//					break;
//				list.setCode(0, rs.getInt("code"));
//				list.setTitle(0, rs.getString("title"));
//				list.setWriter(0, rs.getString("writer"));
//				list.setPrice(0, rs.getInt("price"));
//				list.setRent(0, rs.getInt("rent"));
//				list.setCount(0, rs.getInt("count"));
//			}
//			if (!rs.next())
//				list.setFirstPage(true);
//		} catch (Exception e) {
//			throw new ServletException(e);
//		} finally {
//			try {
//				stmt.close();
//			} catch (Exception ignored) {
//			}
//			try {
//				conn.close();
//			} catch (Exception ignored) {
//			}
//		}
//		return list;
//	}
//	
//	
//	private BookList readNextPage(int upperCode) throws ServletException {
//		BookList list = new BookList();
//		Connection conn = null;
//		Statement stmt = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root", "1234567890");
//			if (conn == null)
//				throw new Exception("데이터베이스에 연결할 수 없습니다.");
//			stmt = conn.createStatement();
//			ResultSet rs = stmt
//					.executeQuery("select * from booksinfo where code < " + upperCode + " order by code desc limit 6;");
//			for (int cnt = 0; cnt < 5; cnt++) {
//				if (!rs.next())
//					break;
//				list.setCode(cnt, rs.getInt("code"));
//				list.setTitle(cnt, rs.getString("title"));
//				list.setWriter(cnt, rs.getString("writer"));
//				list.setPrice(cnt, rs.getInt("price"));
//				list.setRent(cnt, rs.getInt("rent"));
//				list.setCount(cnt, rs.getInt("count"));
//			}
//			if (!rs.next())
//				list.setLastPage(true);
//		} catch (Exception e) {
//			throw new ServletException(e);
//		} finally {
//			try {
//				stmt.close();
//			} catch (Exception ignored) {
//			}
//			try {
//				conn.close();
//			} catch (Exception ignored) {
//			}
//		}
//		return list;
//	}
	
	private BookList readPage(int pageNo) throws ServletException {
		BookList list = new BookList();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root", "1234567890");
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select t.* from "
					+ "( select @rownum := @rownum + 1 as rownum, booksinfo.* from booksinfo, (select @rownum :=0) r order by code asc ) "
					+ "t where t.rownum between " + (5 * (pageNo - 1) + 1) + " and " + (5 * pageNo + 1) + ";");
			
			
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
			if (pageNo == 1)
				list.setFirstPage(true);
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
	
	
	private int readPageNum() throws ServletException {
		int pageNum;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root", "1234567890");
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) as NUM from booksinfo;");
			if (!rs.next())
				return 0;
			pageNum = rs.getInt("NUM");
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
		return (pageNum + 4) / 5;
	}
}