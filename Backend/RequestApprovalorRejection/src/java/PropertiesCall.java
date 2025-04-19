
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class PropertiesCall {
    
    public static void main (String [] args) throws FileNotFoundException, IOException {
        String filePath = "./src/java/config.properties";
        Properties prop = new Properties();
        String url;
        
        FileInputStream ip = new FileInputStream(filePath);
        prop.load(ip);
        
        url = prop.getProperty("repending_url");
        System.out.println("Prop: "+ url);
    }
}
