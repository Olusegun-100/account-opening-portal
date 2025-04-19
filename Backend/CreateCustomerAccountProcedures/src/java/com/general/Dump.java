/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

/**
 *
 * @author User
 */
public class Dump {
    
    /*
    URL account_url= new URL("http://105.112.2.19:8080/ACTProcedure/webresources/ActProcedure");
               HttpURLConnection account_conn = (HttpURLConnection) account_url.openConnection();

               account_conn.setRequestMethod("POST");
               account_conn.setRequestProperty("Accept", "application/json");
               account_conn.setRequestProperty("Content-Type", "application/json; charset=utf8");
               //con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                   
                account_conn.setDoOutput(true);                
               
                try(OutputStream account_os = account_conn.getOutputStream()) {
                    byte[] account_input = content.getBytes("utf-8");
                    account_os.write(account_input, 0, account_input.length);			
                }               
                System.out.println("code = "+account_conn.getResponseCode());
                System.out.println("connection = "+account_conn.getResponseCode());
                System.out.println("request = "+generalmodel.account_toString());
                
                if (account_conn.getResponseCode() == 200) {
                    
                    InputStreamReader in = new InputStreamReader(account_conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;
                    
                    while ((output = br.readLine()) != null) {
                        HashMap<String,Object> resp = new ObjectMapper().readValue(output, HashMap.class);
                        myResponse.setStatuscode(resp.get("statuscode").toString());
                        myResponse.setStatusmessage(resp.get("statusmessage").toString());   
                        myResponse.setAcctNo(resp.get("acctNo").toString()); 
                        
                        if(resp != null) {
                            
                        }
                                                
                        System.out.println("output 111: " + resp);
                    }
//                    System.out.println("out: "+output);
                }else{
                    myResponse.setStatuscode("96");
                    myResponse.setStatusmessage("Faild 001");
                }         
                    
                }   
                catch(Exception ex) {
                    ex.printStackTrace();
                }        
                }                
               );
        return myResponse;
    */
    
}
