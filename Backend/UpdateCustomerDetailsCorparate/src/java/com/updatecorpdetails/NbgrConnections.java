package com.updatecorpdetails;

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

 
    public Connection mySqlDBconnection() {
        
    
        try {          

           
            String url = "jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url);          
            return con;

        } catch (Exception e) {

            return null;

        }

    }//end myConnection()

   
}
