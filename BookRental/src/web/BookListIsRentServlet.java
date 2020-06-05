package web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class BookListIsRentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String strPageNo = request.getParameter("PAGE_NO");
		String rentFlag = request.getParameter("RENT");
		int rent;
		BookList list;
		if("1".equals(rentFlag))
			rent = 1;
		else 
			rent = 0;
				
		if(strPageNo == null)
			strPageNo = "1";
		list = readPage(Integer.parseInt(strPageNo), rent);
		list.setPageNum(readPageNum(rent));
		
		request.setAttribute("BOOK_LIST", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BookListViewIsRent.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private BookList readPage(int pageNo, int rent) throws ServletException {
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
					+ "( select @rownum := @rownum + 1 as rownum, booksinfo.* from booksinfo, (select @rownum :=0) r where rent=" + rent + " order by code asc ) "
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
	
	
	private int readPageNum(int rent) throws ServletException {
		int pageNum;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root", "1234567890");
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) as NUM from booksinfo where rent=" + rent + ";");
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