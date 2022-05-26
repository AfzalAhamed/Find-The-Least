
package Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    Connection con1;
    PreparedStatement insert;

    private void connectDB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/cisgame","root","");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet getData(String query)
    {
        ResultSet rs = null;
        try {
            connectDB();
            insert = con1.prepareStatement(query);
            rs=insert.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void insertData(String query)
    {
        try {
            connectDB();
            insert = con1.prepareStatement(query);
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
