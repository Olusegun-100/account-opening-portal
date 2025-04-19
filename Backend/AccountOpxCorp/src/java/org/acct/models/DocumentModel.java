/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acct.models;

/**
 *
 * @author User
 */
public class DocumentModel {

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return the general_Id
     */
    public String getGeneral_Id() {
        return general_Id;
    }

    /**
     * @param general_Id the general_Id to set
     */
    public void setGeneral_Id(String general_Id) {
        this.general_Id = general_Id;
    }
        
   private String doc_id;
   private String doc_location;
   private String doc_type;        
   private String file;
   private String file_name;
   private String date_log;
   private String general_Id;

    /**
     * @return the doc_id
     */
    public String getDoc_id() {
        return doc_id;
    }

    /**
     * @param doc_id the doc_id to set
     */
    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    /**
     * @return the doc_location
     */
    public String getDoc_location() {
        return doc_location;
    }

    /**
     * @param doc_location the doc_location to set
     */
    public void setDoc_location(String doc_location) {
        this.doc_location = doc_location;
    }

    /**
     * @return the doc_type
     */
    public String getDoc_type() {
        return doc_type;
    }

    /**
     * @param doc_type the doc_type to set
     */
    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    /**
     * @return the file
     */
 
    /**
     * @return the date_log
     */
    public String getDate_log() {
        return date_log;
    }

    /**
     * @param date_log the date_log to set
     */
    public void setDate_log(String date_log) {
        this.date_log = date_log;
    }

    /**
     * @return the file_name
     */
    public String getFile_name() {
        return file_name;
    }

    /**
     * @param file_name the file_name to set
     */
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

}
