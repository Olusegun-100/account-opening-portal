/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.getcounts;

import com.database.ApiResponse;
import com.database.Rep;
import com.getcountsModel.MyResponse;
import encryption.Decrypt;
import encryption.Encrypt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * REST Web Service
 *
 * @author User
 */
@Path("/getCounts")
public class GetCountssResource {
    Connection con = null; 
    ResultSet rs = null;
    PreparedStatement stmt = null;
    

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetCountssResource
     */
    public GetCountssResource() {
    }

    /**
     * Retrieves representation of an instance of com.getcounts.GetCountssResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getCounts() throws ClassNotFoundException {
        //TODO return proper representation object
        return null;
    }
    /**
     * POST method for creating an instance of GetCountsResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse postJson(String content) throws Exception {
//    public MyResponse postJson(String content, @HeaderParam("Auth") String sessionId) {
        MyResponse myResponse = new MyResponse();
        
        try {
            Rep rep = new Rep();
            HashMap<String, String> request = new ObjectMapper().readValue(content, HashMap.class);
            System.out.println("DATA: " + content);
            
            if(request.containsKey("data")) {
                
                 String data = Decrypt.decryptAES(request.get("data"), "ILoveJesusPlenty");
                    System.out.println("data = "+data);

                    Map<String ,String> userDetails = new ObjectMapper().readValue(data, Map.class);

                       
                if(userDetails.get("staffId").equals(userDetails.get("supervisorId"))) {
                int totalsupervisorpendingcount = getSupervisorPendingCount(userDetails.get("supervisorId"));
                int totalsupervisorapprovedcount = getSupervisorApprovedCount(userDetails.get("supervisorId"));
                int totalsupervisorrejectedcount = getSupervisorRejectedCount(userDetails.get("supervisorId"));
                int totalsupervisorhalfrejectedcount = getSupervisorHalfRejectedCount(userDetails.get("supervisorId"));
                
                myResponse.setStatuscode("00");
                myResponse.setStatusmessage("Successful");
                myResponse.setPendingcounts(String.valueOf(totalsupervisorpendingcount));
                myResponse.setApprovedcounts(String.valueOf(totalsupervisorapprovedcount));
                myResponse.setRejectedcounts(String.valueOf(totalsupervisorrejectedcount));
                myResponse.setRependingcounts(String.valueOf(totalsupervisorhalfrejectedcount));
                myResponse.setData(myResponse.toString());                
                
               
            } else {
                int totalinitiatorpendingcount = getInitiatorPendingCount(userDetails.get("staffId"));
                int totalinitiatorapprovedcount = getInitiatorApprovedCount(userDetails.get("staffId"));
                int totalinitiatorrejectedcount = getInitiatorRejectedCount(userDetails.get("staffId"));
                int totalinitiatorhalfrejectedcount = getInitiatorHalfRejectedCount(userDetails.get("staffId"));
                myResponse.setPendingcounts(String.valueOf(totalinitiatorpendingcount));
                myResponse.setApprovedcounts(String.valueOf(totalinitiatorapprovedcount));
                myResponse.setRejectedcounts(String.valueOf(totalinitiatorrejectedcount));                
                myResponse.setRependingcounts(String.valueOf(totalinitiatorhalfrejectedcount));   
                myResponse.setData(myResponse.toString());
                myResponse.setStatuscode("00");
                myResponse.setStatusmessage("Successful");
               
            }
            } else {
                myResponse.setStatuscode("96");
                myResponse.setStatusmessage("Invalid Request");
            }
             
        } 
        
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        ApiResponse apiResponse = new ApiResponse();
        
        apiResponse.setData(Base64.getEncoder().encodeToString(Encrypt.encryptAES(myResponse.toString())));
        return apiResponse;
}
    
    private int getSupervisorPendingCount(String approverId){
        System.out.println("approverId = "+approverId);
        int pendingaccountcount = 0;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where status ='pending' AND approverId='"+approverId+"'");
//            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where status ='pending' AND staffId='"+staffId+"'");
//            String query= "SELECT COUNT(*) FROM actdb.customer a where status= ? AND staffId= ? ";
//            
//            stmt = con.prepareStatement(query);
//            stmt.setString(1, "");
//            stmt.setString(2, staffId);
            
            if(rs.next()) {
                pendingaccountcount = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pendingaccountcount;
    }
    
    private int getSupervisorApprovedCount(String staffId) {
        
        int approvedaccountcount = 0;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where status='approve' and approverId='"+staffId+"'");

//            String query= "SELECT COUNT(*) FROM actdb.customer a where status=? and staffId= ?";
//            
//            stmt = con.prepareStatement(query);
//            stmt.setString(1, "approve");
//            stmt.setString(2, staffId);
            
            if(rs.next()) {
                approvedaccountcount = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return approvedaccountcount;
    }
    
    private int getSupervisorRejectedCount(String staffId) {
        
        int rejectedaccountcount = 0;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where  status ='rejected' AND approverId='"+staffId+"'");
//            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where (status= 'repending' or status ='rejected') AND staffId='"+staffId+"'");
            
            //SELECT COUNT(*) FROM actdb.customer a where (status= 'repending' or status ='pending') AND staffId='"+staffId+"'
//            String query= "SELECT COUNT(*) FROM actdb.customer a where status=? AND staffId= ?";
//            
//            stmt = con.prepareStatement(query);
//            stmt.setString(1, "rejected");
//            stmt.setString(2, staffId);
            
            if(rs.next()) {
                rejectedaccountcount = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rejectedaccountcount;
    }
    
    private int getSupervisorHalfRejectedCount(String staffId) {
        
        int rejectedaccountcount = 0;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where status= 'repending' AND approverId='"+staffId+"'");
            
            //SELECT COUNT(*) FROM actdb.customer a where (status= 'repending' or status ='pending') AND staffId='"+staffId+"'
//            String query= "SELECT COUNT(*) FROM actdb.customer a where status=? AND staffId= ?";
//            
//            stmt = con.prepareStatement(query);
//            stmt.setString(1, "rejected");
//            stmt.setString(2, staffId);
            
            if(rs.next()) {
                rejectedaccountcount = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rejectedaccountcount;
    }

    
    private int getInitiatorPendingCount(String staffId){
        
        int pendingInitiatoraccountcount = 0;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where status=''");
//            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where status='' or (status='initiator-repending' AND staffId='"+staffId+"')");
//            String query= "SELECT COUNT(*) FROM actdb.customer a where status=? AND staffId= ?";
//            
//            stmt = con.prepareStatement(query);
//            stmt.setString(1, null);
//            stmt.setString(2, staffId);
            
            if(rs.next()) {
                pendingInitiatoraccountcount = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pendingInitiatoraccountcount;
    }
    
    
    private int getInitiatorApprovedCount(String staffId) {
        
        int approvedaccountcount = 0;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where status='pending' and staffId='"+staffId+"'");
            
//            String query= "SELECT COUNT(*) FROM actdb.customer a where status=? and staffId= ?";
//            
//            stmt = con.prepareStatement(query);
//            stmt.setString(1, "pending");
//            stmt.setString(2, staffId);
//            
            if(rs.next()) {
                approvedaccountcount = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return approvedaccountcount;
    }
    
    private int getInitiatorRejectedCount(String staffId) {
        
        int initiatedrejectedaccountcount = 0;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where status='initiator-rejected' AND staffId= '"+staffId+"'");
            
            //SELECT COUNT(*) FROM actdb.customer a where status='' or (status='initiator-repending' AND staffId='
//            String query= "SELECT COUNT(*) FROM actdb.customer a where status=?, approved_date=? AND staffId= ?";
//            
//            stmt = con.prepareStatement(query);
//            stmt.setString(1, "rejected");
//            stmt.setString(2, null);
//            stmt.setString(3, staffId);
            
            if(rs.next()) {
                initiatedrejectedaccountcount = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return initiatedrejectedaccountcount;
    }

     private int getInitiatorHalfRejectedCount(String staffId) {
        
        int initiatedrejectedaccountcount = 0;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM actdb.customer a where status='initiator-repending' AND staffId='"+staffId+"'");
            
            //SELECT COUNT(*) FROM actdb.customer a where status='' or (status='initiator-repending' AND staffId='
//            String query= "SELECT COUNT(*) FROM actdb.customer a where status=?, approved_date=? AND staffId= ?";
//            
//            stmt = con.prepareStatement(query);
//            stmt.setString(1, "rejected");
//            stmt.setString(2, null);
//            stmt.setString(3, staffId);
            
            if(rs.next()) {
                initiatedrejectedaccountcount = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCountssResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return initiatedrejectedaccountcount;
    }

    

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public GetCountsResource getGetCountsResource(@PathParam("id") String id) {
        return GetCountsResource.getInstance(id);
    }
}
