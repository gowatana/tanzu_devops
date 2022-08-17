package devTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServlet;

public class DBManager extends HttpServlet {
    
    private Connection conn = null; // コネクション情報
    private Statement stmt = null;

    /**
     * コンストラクタ.
     * @throws Exception 処理例外
     */
	public DBManager(String dir) throws Exception {
		
	    // データベース接続に使用する情報
	    String jdbcUser = "";
	    String jdbcPass = "";
	    String jdbcUrl = "";
		
        try {
        	File dicDir = Paths.get(dir).toFile();
        	URLClassLoader urlc = new URLClassLoader(new URL[] {dicDir.toURI().toURL()});
        	ResourceBundle re = ResourceBundle.getBundle("devTest", Locale.getDefault(), urlc);
        	
            jdbcUser = re.getString("JDBC_USER");
            jdbcPass = re.getString("JDBC_PASS");
            jdbcUrl = re.getString("JDBC_URL");

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("プロパティファイル取得不可");
        }
        
        try{
        	Class.forName("org.postgresql.Driver");
            //PostgreSQLへ接続
            conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);

            //自動コミットOFF
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            
        }catch (Exception e) {
        	 e.printStackTrace();
			throw new Exception("DB接続エラー：" + e.getMessage());
		}
	}
    
	/**
	 * コネクションクローズ処理.
	 */
    public void close(){
        try {
            if(stmt != null)stmt.close();
            if(conn != null)conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

}
