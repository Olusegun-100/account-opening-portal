package org.acct.opening;

/**
 * @author Abobade Oludayo Michael
 * @email:pagims2003@yahoo.com , michael.abobade@lolikshouse.com
 * @version 1.0
 * @mobile 08065711043, 08077792196
 * @Date 2017-01-28
 */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
 public class NbgrConnections implements Serializable {

    private String messangerOfTruth;
    private boolean testconnection;
 
    public Connection mySqlDBconnection() {
        
    
        try {          

           
            String url = "jdbc:mysql://105.112.2.19:3306/actdb?user=root&password=gims";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url);          
            return con;

        } catch (Exception e) {

            return null;

        }

    }//end myConnection()

   
}
