package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

public class BookSearchServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getMethod();
		String searchMode = request.getParameter("chk_info");
		String searchContent = request.getParameter("Search");
		
		BookList list;
		list = searchBook(searchMode, searchContent);

		request.setAttribute("BOOK_LIST", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WebTemplate.jsp?BODY_PATH=BookListView.jsp?MODE=search");
		dispatcher.forward(request, response);
	}

	private BookList searchBook(String searchMode, String searchContent) throws ServletException {
		BookList list = new BookList();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/bookrent?serverTimezone=UTC", "root",
					"1234567890");
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from booksinfo where " + searchMode + " like '%" + searchContent +"%';");
			for (int cnt = 0; cnt < 5; cnt++) {
				if (rs.next()) {
					list.setCode(cnt, rs.getInt("code"));
					list.setTitle(cnt, rs.getString("title"));
					list.setWriter(cnt, rs.getString("writer"));
					list.setPrice(cnt, rs.getInt("price"));
					list.setRent(cnt, rs.getInt("rent"));
					list.setCount(cnt, rs.getInt("count"));
					System.out.println(rs.getInt("code"));
				}
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
		return list;
	}
}
