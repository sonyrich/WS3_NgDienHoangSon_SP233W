package sonndh.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBUtils implements Serializable {

    public static Connection makeConnection() throws SQLException {
        /* Old Style Connection
        try {
            //1. Load Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2. Tao connection
            //string protocol: loaiserver://diachi IP:port; tendb; teninstance
            String url;
            url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=Sonndh_SP23_3W;"
                    + "instanceName=SQLEXPRESS";
            //3. Lay connection
            Connection con = DriverManager.getConnection(url, "sa", "12345");
            //4. Tra connection cho doi tuong goi
            return con;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
         */
        //New Style Connection
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("Spring2023");
            Connection con = ds.getConnection();
            return con;
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
