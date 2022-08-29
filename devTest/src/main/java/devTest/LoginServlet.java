package devTest;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private String dir = "";
	
	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public LoginServlet() {
		super();
	}
	
	public void init() {
		String d = this.getServletContext().getInitParameter("directory");
		
		if(d == null || d == "") {
			d = "/opt/tomcat_inst_01/tomcat/conf";
		}
		
		setDir(d);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String errmMessage = "";
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");
       	TopBean top = new TopBean();
       	
        if(isNull(id) || isNull(pass)) {
        	errmMessage  = "ID、パスワードは必須入力です!";
        }else {
        	DBManager db = null;
        	ResultSet rs = null;
        	try {

        		db= new DBManager(getDir());
        		String sql = "SELECT first_name, last_name FROM users WHERE id = ? AND password = ?";
        		PreparedStatement ps = db.getConn().prepareStatement(sql);

        		ps.setString(1, id);
        		ps.setString(2, pass);

        		rs = ps.executeQuery();

        		if (rs.next()) {
        			// 見つかったアカウント情報を戻り値にセット
        			top.setName(rs.getString("first_name") + " " + rs.getString("last_name"));
        		} else {
        			errmMessage  = "IDもしくはパスワードに誤りがあります。";
        		}
        	} catch (Exception e) {
        		errmMessage = "DBの接続に失敗しました。";
        		e.printStackTrace();
        	}finally {
        		if(db != null) {
        			db.close();
        		}
        		if(rs != null) {
        			try {
        				rs.close();
        			} catch (SQLException e) {
        				errmMessage = "DBのクローズに失敗しました。";
        				e.printStackTrace();
        			}
        		}
        	}
        }

        // セッションにアカウント情報＆ロールを登録
        if(isNull(errmMessage)) {
        	request.setAttribute("top", top);
	
	        RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
	        rd.forward(request, response);
        }else {
        	request.setAttribute("errMessage", errmMessage);
        	request.setAttribute("id", id);
        	request.setAttribute("pass", pass);
	    	
	        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	        rd.forward(request, response);
        }
	}
	
	private boolean isNull(String val) {
		return val == null || val == "" || val.length() == 0;
	}
}
