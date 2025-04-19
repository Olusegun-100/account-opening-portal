/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.request;

import com.rep.MyResponse;
import com.rep.ProcedureModel;
import com.rep.Repo;
import com.rep.RequestModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/approveOreject")
public class ApproveRejectRequestsResource {

    @Context
    private UriInfo context;
    
    private List<String> decision = Arrays.asList("approve", "reject", "rejected", "repending","initiator-repending", "initiator-rejected");
    /**
     * Creates a new instance of ApproveRejectRequestsResource
     */
    public ApproveRejectRequestsResource() {
    }

    /**
     * Retrieves representation of an instance of com.request.ApproveRejectRequestsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of ApproveRejectRequestResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }
                
    
    /**
     *
     * @param id
     * @param request
     * @return
     */
    @PUT    
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse updateAccount(String content) throws Exception {
        //TODO return proper representation object>
        MyResponse myResponse = new MyResponse();
//        String filePath = "./src/java/config.properties";
//        String filePath = "C:/Users/User/Documents/NetBeansProjects/RequestApprovalorRejection/src/java/config.properties";
//        Properties prop = new Properties();
//        String url;
//        
//        FileInputStream ip = new FileInputStream(filePath);
//        prop.load(ip);
//        
//        url = prop.getProperty("repending_url");
////        System.out.println("Prop: "+ url);

//        myResponse.setStatuscode("96");
//        myResponse.setStatusmessage("Failure");
        
        try {
            
            RequestModel request = new ObjectMapper().readValue(content, RequestModel.class);
            myResponse.setData(request);
            Repo repo = new Repo();
            
            String account_status = repo.customerStatus(request.getId()).get("status");
            String staffId = repo.StaffId(request.getId());
            String approverId = repo.ApproverId(request.getId());
            HashMap<String, String> customer_details = repo.customerStatus(request.getId());
            String approver_email = repo.ApproverEmail(request.getApproverId());

            System.out.println("Approver Id email is : " +approver_email);
            if(request.getDespondent() != null && request.getDecision().equalsIgnoreCase("reject")){
                if(request.getStaffId().equals(request.getApproverId())){
                    if(request.getDespondent().equalsIgnoreCase("half")){
                        request.setDecision("repending");
                    }else if(request.getDespondent().equalsIgnoreCase("full")){
                        request.setDecision("rejected");
                    }
                } else {
                    if(request.getDespondent().equalsIgnoreCase("half")){
                        request.setDecision("initiator-repending");
                    }else if(request.getDespondent().equalsIgnoreCase("full")){
                        request.setDecision("initiator-rejected");
                    } 
                }
            }
            
            if(!customer_details.isEmpty()){
                String email = customer_details.get("email");
                if(request.getStaffId().equalsIgnoreCase(request.getApproverId())){
//                System.out.println("decision 2");
                if(staffId != null && approverId != null && account_status.equalsIgnoreCase("pending")){
                    if((request.getApproverId()).equalsIgnoreCase(approverId)){
                        
                            boolean isMailSent = false;
                        if(request.getDecision().equalsIgnoreCase("repending") || request.getDecision().equalsIgnoreCase("rejected")){
                            repo.approveRequest(request.getApproverId(), request.getStaffId(), request.getId(), request.getDecision(), "");
                            isMailSent = sendEmail(email, 
                                    request.getDecision().equalsIgnoreCase("repending") 
                                            ? request.getComment()+" complete your registration with this url. http://app.infinitytrustmortgagebank.com:8080/#/r/personal-information/"+request.getId()
                                            : request.getComment()
                                            , "ITMB Account Opening");
                            myResponse.setStatusmessage((isMailSent)? "Success!": "Success, Mail not delivered");
                            myResponse.setStatuscode("00");
                        }else{
                            System.out.println("proc 1");
                            ProcedureModel proceduremodel = repo.procedureDetails(request.getId());  
                            System.out.println("proc 2");
                            HashMap<String,Object> respy = repo.AccountNumber(proceduremodel.customer_toString());                            
                            System.out.println("proc 3 "+respy);
                            if(!respy.get("statuscode").equals("96")){
                                if(!respy.get("acctNo").equals(null)){                                    
                                    String accNum = respy.get("acctNo").toString();
                                    String cusId = respy.get("cusId").toString();
                                    System.out.println("AAAAA: " +accNum);
                                    System.out.println("CCCCC:" +cusId);
                                    System.out.println("ARK: " +respy);
                                    repo.approveRequest(request.getApproverId(), request.getStaffId(), request.getId(), request.getDecision(), cusId);
                                    repo.updateAccountNumber(accNum, request.getId());
                                    isMailSent = sendEmail(email, "Thank you for opening account with us, your account number is: " +accNum, "ITMB Account Opening");;
                                    myResponse.setStatusmessage((isMailSent)? "Success!": "Success, Mail not delivered");
                                    myResponse.setStatuscode("00");
                                }
                            }else{
                                myResponse.setStatuscode("96");
                                myResponse.setStatusmessage("CustomerId already exist!");
                            }                           
                        }                        
                    } else {
                        myResponse.setStatuscode("00");
                        myResponse.setStatusmessage("Approver does not exist!");
                    }
                   
                }else{
                    myResponse.setStatuscode("96");
                    myResponse.setStatusmessage("No pending approver!");   
                }
            }else if(!request.getStaffId().equalsIgnoreCase(request.getApproverId())){
//                System.out.println("decision 3");
                if(decision.contains(request.getDecision())){
//                    System.out.println("decision 4");
                    if(account_status.isEmpty()){
                        boolean isMailSent = false;
                        repo.initiateRequest(request.getApproverId(), request.getStaffId(), request.getId(), request.getDecision());
                        isMailSent = sendEmail(approver_email, 
                                    "A request has been initiated, Please check your portal", "Account Opening Request");
                        repo.updateOfficer(request.getAcct_officer(),request.getId());
                        myResponse.setStatusmessage((isMailSent)?"Success!": "Success, Mail not delivered"); 
                        if(request.getDecision().equalsIgnoreCase("initiator-repending") || request.getDecision().equalsIgnoreCase("initiator-rejected")){
                            isMailSent = sendEmail(email, 
                                    request.getDecision().equalsIgnoreCase("initiator-repending") 
                                            ? request.getComment()+" complete your registration with this url. http://app.infinitytrustmortgagebank.com:8080/#/r/personal-information/"+request.getId()
                                            : request.getComment()
                                        , "ITMB Account Opening");
                            myResponse.setStatusmessage((isMailSent)? "Success!": "Success, Mail not delivered"); 
                        }
                        myResponse.setStatuscode("00");
                    }else{
                        myResponse.setStatuscode("96");
                        myResponse.setStatusmessage("Nothing to approve!");   
                    }
                }else{
                    System.out.println("decision ");
                    myResponse.setStatuscode("96");
                    myResponse.setStatusmessage("Invalid decision!");
                }
            }
            }else{
                myResponse.setStatuscode("96");
                myResponse.setStatusmessage("AccounInvalid customer Id!");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            myResponse.setStatuscode("96");
            myResponse.setStatusmessage("Failed, Customer already exists!");

        }    
        return myResponse;
        
    }
    
    public boolean sendEmail(String messageTo, String message, String subject) throws FileNotFoundException, IOException{
        
//        String filePath_two = "C:/Users/User/Documents/NetBeansProjects/RequestApprovalorRejection/src/java/config.properties";
//        Properties prop = new Properties();
//        String url_two;
//        
//        FileInputStream ip_two = new FileInputStream(filePath_two);
//        prop.load(ip_two);
//        
//        url_two = prop.getProperty("email_url");
//     
       
       String email_string = "{"
                + "\"msgTo\":\"" + messageTo + "\",\n"
                + "\"msgsubject\":\"" + subject + "\",\n"
               + "\"msgSender\":\"ITMB Bank\""+",\n"
                + "\"msgText\":\"" + message + "\"\n"
            + "}";
        System.out.println("email body = "+email_string);
            try {
//                URL email_url= new URL(url_two);
                URL email_url= new URL("http://app.infinitytrustmortgagebank.com:8080/newEmail/webresources/sendemail");

                HttpURLConnection email_conn = (HttpURLConnection) email_url.openConnection();
                email_conn.setRequestMethod("POST");
                email_conn.setRequestProperty("Content-Type", "application/json");
                email_conn.setDoOutput(true);
                 try(OutputStream email_os = email_conn.getOutputStream()) {
                    byte[] email_input = email_string.getBytes("utf-8");
                    email_os.write(email_input, 0, email_input.length);  
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                 System.out.println("email code = "+email_conn.getResponseCode());
                return (email_conn.getResponseCode() == 200);
                    
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public ApproveRejectRequestResource getApproveRejectRequestResource(@PathParam("id") String id) {
        return ApproveRejectRequestResource.getInstance(id);
    }
}
