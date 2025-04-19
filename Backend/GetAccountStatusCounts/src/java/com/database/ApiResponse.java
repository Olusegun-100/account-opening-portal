/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;


/**
 *
 * @author User
 */
public 
 
    class ApiResponse{
        private String data;
       
        /**
         * @return the data
         */
        public String getData() {
            return data;
        }

        /**
         * @param data the data to set
         */
        public void setData(String data) {
            this.data= data;
            System.out.println("API Response = "+this.data);
        }
        
    }

