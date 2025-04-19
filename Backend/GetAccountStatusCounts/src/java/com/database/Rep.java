package com.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class Rep {
    Connection con = null; 
    ResultSet rs = null;
    PreparedStatement stmt = null;
    
    public Rep() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
    }
    
    public String staffId(){
        String query = "SELECT staffId from actdb.customer";

        try {
           stmt = con.prepareStatement(query);

           ResultSet rst = stmt.executeQuery();
           if(rst.next()){
               return rst.getString("staffId");
           }
           return null;
        }catch(Exception e){
            e.printStackTrace();
        } 
        return null;
    }
    
    public String supervisorId(String staffId){
        String query = "SELECT approverId from actdb.customer WHERE staffId = '"+staffId+"'";

        try {
           stmt = con.prepareStatement(query);

           ResultSet rst = stmt.executeQuery();
           if(rst.next()){
               return rst.getString("approverId");
           }
           return null;
        }catch(Exception e){
            e.printStackTrace();
        } 
        return null;
    }
    
    
    
//    
//        public String Res(String approverId){
//        
//        HashMap<String, String> data = new HashMap(){{
//            put("staffId", null);
//            put("approverId", null);
//        }};
//        
//         try {
//           
//           String query = "SELECT staffId FROM actdb.customer where approverId='"+approverId+"'";
//             System.out.println("staff query = "+query);
//           stmt = con.prepareStatement(query);
//           ResultSet rst = stmt.executeQuery(); 
//            if(rst.next()){
//                data.put("staffId", rst.getString("staffId"));
////                data.put("approverId", rst.getString("approverId"));
//                
//                return rst.getString("staffId");
//            }
////            return null;
//         }catch(Exception e){
//             e.printStackTrace();
//         } 
//         return null;
//    }
//    
    
}
