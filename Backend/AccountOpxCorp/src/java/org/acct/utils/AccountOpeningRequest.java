/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acct.utils;

import java.util.HashMap;
import org.acct.models.AccountModel;
import org.acct.models.AddressModel;
//import org.acct.models.CustomerModel;
import org.acct.models.DocumentModel;
import org.acct.models.PersonalDetailsModel;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.acct.models.AccountheldModel;
import org.acct.models.ContactPersonModel;
import org.acct.models.SignatoryModel;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author User
 */
public class AccountOpeningRequest {

    /**
     * @return the transactionalId
     */
    public String getTransactionalId() {
        return transactionalId;
    }

    /**
     * @param transactionalId the transactionalId to set
     */
    public void setTransactionalId(String transactionalId) {
        this.transactionalId = transactionalId;
    }

    /**
     * @return the accountservices
     */
    public String getAccountservices() {
        return accountservices;
    }

    /**
     * @param accountservices the accountservices to set
     */
    public void setAccountservices(String accountservices) {
        this.accountservices = accountservices;
    }
    /**
     * @return the companyquotedname
     */
    public String getCompanyquotedname() {
        return companyquotedname;
    }

    /**
     * @param companyquotedname the companyquotedname to set
     */
    public void setCompanyquotedname(String companyquotedname) {
        this.companyquotedname = companyquotedname;
    }

    /**
     * @return the agentname
     */
    public String getAgentname() {
        return agentname;
    }

    /**
     * @param agentname the agentname to set
     */
    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }
    /**
     * @return the contactperson
     */
    public List<ContactPersonModel> getContactperson() {
        return contactperson;
    }

    /**
     * @param contactperson the contactperson to set
     */
    public void setContactperson(List<ContactPersonModel> contactperson) {
        this.contactperson = contactperson;
    }

    /**
     * @return the signatories
     */
    public List<SignatoryModel> getSignatories() {
        return signatories;
    }

    /**
     * @param signatories the signatories to set
     */
    public void setSignatories(List<SignatoryModel> signatories) {
        this.signatories = signatories;
    }

    /**
     * @return the accounheld
     */
    public List<AccountheldModel> getAccounheld() {
        return accounheld;
    }

    /**
     * @param accounheld the accounheld to set
     */
    public void setAccounheld(List<AccountheldModel> accounheld) {
        this.accounheld = accounheld;
    }

    /**
     * @return the dateofincorparation
     */
    public String getDateofincorparation() {
        return dateofincorparation;
    }

    /**
     * @param dateofincorparation the dateofincorparation to set
     */
    public void setDateofincorparation(String dateofincorparation) {
        this.dateofincorparation = dateofincorparation;
    }

    /**
     * @return the jurisdificationofincorparation
     */
    public String getJurisdificationofincorparation() {
        return jurisdificationofincorparation;
    }

    /**
     * @param jurisdificationofincorparation the jurisdificationofincorparation to set
     */
    public void setJurisdificationofincorparation(String jurisdificationofincorparation) {
        this.jurisdificationofincorparation = jurisdificationofincorparation;
    }

    /**
     * @return the operatingbusinessaddress
     */
    public String getOperatingbusinessaddress() {
        return operatingbusinessaddress;
    }

    /**
     * @param operatingbusinessaddress the operatingbusinessaddress to set
     */
    public void setOperatingbusinessaddress(String operatingbusinessaddress) {
        this.operatingbusinessaddress = operatingbusinessaddress;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the crm
     */
    public String getCrm() {
        return crm;
    }

    /**
     * @param crm the crm to set
     */
    public void setCrm(String crm) {
        this.crm = crm;
    }

    /**
     * @return the specialcontrolunit
     */
    public String getSpecialcontrolunit() {
        return specialcontrolunit;
    }

    /**
     * @param specialcontrolunit the specialcontrolunit to set
     */
    public void setSpecialcontrolunit(String specialcontrolunit) {
        this.specialcontrolunit = specialcontrolunit;
    }

    /**
     * @return the iscompanyquoted
     */
    public String getIscompanyquoted() {
        return iscompanyquoted;
    }

    /**
     * @param iscompanyquoted the iscompanyquoted to set
     */
    public void setIscompanyquoted(String iscompanyquoted) {
        this.iscompanyquoted = iscompanyquoted;
    }

    /**
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * @return the lgaName
     */
    public String getLgaName() {
        return lgaName;
    }

    /**
     * @param lgaName the lgaName to set
     */
    public void setLgaName(String lgaName) {
        this.lgaName = lgaName;
    }

    /**
     * @return the nationalityName
     */
    public String getNationalityName() {
        return nationalityName;
    }

    /**
     * @param nationalityName the nationalityName to set
     */
    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    /**
     * @return the genderName
     */
    public String getGenderName() {
        return genderName;
    }

    /**
     * @param genderName the genderName to set
     */
    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    /**
     * @return the maritalstatusName
     */
    public String getMaritalstatusName() {
        return maritalstatusName;
    }

    /**
     * @param maritalstatusName the maritalstatusName to set
     */
    public void setMaritalstatusName(String maritalstatusName) {
        this.maritalstatusName = maritalstatusName;
    }

    /**
     * @return the religionName
     */
    public String getReligionName() {
        return religionName;
    }

    /**
     * @param religionName the religionName to set
     */
    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    /**
     * @return the employstatname
     */
    public String getEmploystatname() {
        return employstatname;
    }

    /**
     * @param employstatname the employstatname to set
     */
    public void setEmploystatname(String employstatname) {
        this.employstatname = employstatname;
    }

    /**
     * @return the incCBracketname
     */
    public String getIncCBracketname() {
        return incCBracketname;
    }

    /**
     * @param incCBracketname the incCBracketname to set
     */
    public void setIncCBracketname(String incCBracketname) {
        this.incCBracketname = incCBracketname;
    }

    /**
     * @return the busTypename
     */
    public String getBusTypename() {
        return busTypename;
    }

    /**
     * @param busTypename the busTypename to set
     */
    public void setBusTypename(String busTypename) {
        this.busTypename = busTypename;
    }

    /**
     * @return the occCodename
     */
    public String getOccCodename() {
        return occCodename;
    }

    /**
     * @param occCodename the occCodename to set
     */
    public void setOccCodename(String occCodename) {
        this.occCodename = occCodename;
    }

    /**
     * @return the sectCodename
     */
    public String getSectCodename() {
        return sectCodename;
    }

    /**
     * @param sectCodename the sectCodename to set
     */
    public void setSectCodename(String sectCodename) {
        this.sectCodename = sectCodename;
    }

    /**
     * @return the employstat
     */
    public String getEmploystat() {
        return employstat;
    }

    /**
     * @param employstat the employstat to set
     */
    public void setEmploystat(String employstat) {
        this.employstat = employstat;
    }

    /**
     * @return the incCBracket
     */
    public String getIncCBracket() {
        return incCBracket;
    }

    /**
     * @param incCBracket the incCBracket to set
     */
    public void setIncCBracket(String incCBracket) {
        this.incCBracket = incCBracket;
    }

    /**
     * @return the busType
     */
    public String getBusType() {
        return busType;
    }

    /**
     * @param busType the busType to set
     */
    public void setBusType(String busType) {
        this.busType = busType;
    }

    /**
     * @return the occCode
     */
    public String getOccCode() {
        return occCode;
    }

    /**
     * @param occCode the occCode to set
     */
    public void setOccCode(String occCode) {
        this.occCode = occCode;
    }

    /**
     * @return the sectCode
     */
    public String getSectCode() {
        return sectCode;
    }

    /**
     * @param sectCode the sectCode to set
     */
    public void setSectCode(String sectCode) {
        this.sectCode = sectCode;
    }

    /**
     * @return the branch_code
     */
    public String getBranch_code() {
        return branch_code;
    }

    /**
     * @param branch_code the branch_code to set
     */
    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    /**
     * @return the account_officer
     */
    public String getAccount_officer() {
        return account_officer;
    }

    /**
     * @param account_officer the account_officer to set
     */
    public void setAccount_officer(String account_officer) {
        this.account_officer = account_officer;
    }

    //Account table
    private String user_id;
    private String acct_description;
    private String acct_no;
    private String acct_type;
    private String time_stamp;
    private String date;
    private String date_open;
    private String currency_code;

    //Address Table
    private String address_description;
    private String country;
    private String email;
    private String lga;
    private String phonenumber;
    private String postal_code;
    private String state;
    private String customer_id;
    private String alt_phonenumber;    
    private String contact_addressone;
    private String contact_addresstwo;
    private String dob;
    private String first_name;
    private String gender;
    private String last_name;
    private String marital_status;
    private String middle_name;
    private String mother_maidenname;
    private String nationality;
    private String permit_expiry_date;
    private String permit_issue_date;
    private String place_of_birth;
    private String place_of_permit_issue;
    private String religion;
    private String resident_permit_number;
    private String residential_address;
    private String residential_address_lga;
    private String residential_address_state;
    private String state_of_origin;
    private String title;
    private String genderName;
    private String maritalstatusName;
    private String religionName;
    private String stateName;
    private String lgaName;
    private String nationalityName;
    
    //Customer Table
    private String custtable_id;
    private String acct_name;    
    private String dateopen;
    private String firstname;
    private String status;
    private String bvn;
    private String full_name;
    private String lastname;
    private String middlename;
    private String rcno;
    private String timestamp;
    private String tin;
    private String branch_code;
    private String account_officer;
    private String employstat;
    private String incCBracket;
    private String busType;
    private String occCode;
    private String sectCode;
    private String employstatname;
    private String incCBracketname;
    private String busTypename;
    private String occCodename;
    private String sectCodename; 
    private String dateofincorparation;
    private String jurisdificationofincorparation;
    private String operatingbusinessaddress;
    private String website;
    private String crm;
    private String specialcontrolunit;
    private String iscompanyquoted;
    private String companyquotedname;
    private String agentname;
    
    private String transactionalId;
    private String accountservices;
//            primary key (id)
        
    //Document table
    private List<DocumentModel> documents;
    
    //Accountheld table
    private List<AccountheldModel> accounheld;
    
    //Signatories table 
    private List<SignatoryModel> signatories;
    
    //ContactPerson Model
    private List<ContactPersonModel> contactperson;
    
    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the acct_description
     */
    public String getAcct_description() {
        return acct_description;
    }

    /**
     * @param acct_description the acct_description to set
     */
    public void setAcct_description(String acct_description) {
        this.acct_description = acct_description;
    }

    /**
     * @return the acct_no
     */
    public String getAcct_no() {
        return acct_no;
    }

    /**
     * @param acct_no the acct_no to set
     */
    public void setAcct_no(String acct_no) {
        this.acct_no = acct_no;
    }

    /**
     * @return the acct_type
     */
    public String getAcct_type() {
        return acct_type;
    }

    /**
     * @param acct_type the acct_type to set
     */
    public void setAcct_type(String acct_type) {
        this.acct_type = acct_type;
    }

    /**
     * @return the time_stamp
     */
    public String getTime_stamp() {
        return time_stamp;
    }

    /**
     * @param time_stamp the time_stamp to set
     */
    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the date_open
     */
    public String getDate_open() {
        return date_open;
    }

    /**
     * @param date_open the date_open to set
     */
    public void setDate_open(String date_open) {
        this.date_open = date_open;
    }

    /**
     * @return the currency_code
     */
    public String getCurrency_code() {
        return currency_code;
    }

    /**
     * @param currency_code the currency_code to set
     */
    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    /**
     * @return the address_description
     */
    public String getAddress_description() {
        return address_description;
    }

    /**
     * @param address_description the address_description to set
     */
    public void setAddress_description(String address_description) {
        this.address_description = address_description;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the lga
     */
    public String getLga() {
        return lga;
    }

    /**
     * @param lga the lga to set
     */
    public void setLga(String lga) {
        this.lga = lga;
    }

    /**
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return the postal_code
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * @param postal_code the postal_code to set
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the customer_id
     */
    public String getCustomer_id() {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @return the alt_phonenumber
     */
    public String getAlt_phonenumber() {
        return alt_phonenumber;
    }

    /**
     * @param alt_phonenumber the alt_phonenumber to set
     */
    public void setAlt_phonenumber(String alt_phonenumber) {
        this.alt_phonenumber = alt_phonenumber;
    }

    /**
     * @return the contact_addressone
     */
    public String getContact_addressone() {
        return contact_addressone;
    }

    /**
     * @param contact_addressone the contact_addressone to set
     */
    public void setContact_addressone(String contact_addressone) {
        this.contact_addressone = contact_addressone;
    }

    /**
     * @return the contact_addresstwo
     */
    public String getContact_addresstwo() {
        return contact_addresstwo;
    }

    /**
     * @param contact_addresstwo the contact_addresstwo to set
     */
    public void setContact_addresstwo(String contact_addresstwo) {
        this.contact_addresstwo = contact_addresstwo;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the marital_status
     */
    public String getMarital_status() {
        return marital_status;
    }

    /**
     * @param marital_status the marital_status to set
     */
    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    /**
     * @return the middle_name
     */
    public String getMiddle_name() {
        return middle_name;
    }

    /**
     * @param middle_name the middle_name to set
     */
    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    /**
     * @return the mother_maidenname
     */
    public String getMother_maidenname() {
        return mother_maidenname;
    }

    /**
     * @param mother_maidenname the mother_maidenname to set
     */
    public void setMother_maidenname(String mother_maidenname) {
        this.mother_maidenname = mother_maidenname;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the permit_expiry_date
     */
    public String getPermit_expiry_date() {
        return permit_expiry_date;
    }

    /**
     * @param permit_expiry_date the permit_expiry_date to set
     */
    public void setPermit_expiry_date(String permit_expiry_date) {
        this.permit_expiry_date = permit_expiry_date;
    }

    /**
     * @return the permit_issue_date
     */
    public String getPermit_issue_date() {
        return permit_issue_date;
    }

    /**
     * @param permit_issue_date the permit_issue_date to set
     */
    public void setPermit_issue_date(String permit_issue_date) {
        this.permit_issue_date = permit_issue_date;
    }

    /**
     * @return the place_of_birth
     */
    public String getPlace_of_birth() {
        return place_of_birth;
    }

    /**
     * @param place_of_birth the place_of_birth to set
     */
    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    /**
     * @return the place_of_permit_issue
     */
    public String getPlace_of_permit_issue() {
        return place_of_permit_issue;
    }

    /**
     * @param place_of_permit_issue the place_of_permit_issue to set
     */
    public void setPlace_of_permit_issue(String place_of_permit_issue) {
        this.place_of_permit_issue = place_of_permit_issue;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return the resident_permit_number
     */
    public String getResident_permit_number() {
        return resident_permit_number;
    }

    /**
     * @param resident_permit_number the resident_permit_number to set
     */
    public void setResident_permit_number(String resident_permit_number) {
        this.resident_permit_number = resident_permit_number;
    }

    /**
     * @return the residential_address
     */
    public String getResidential_address() {
        return residential_address;
    }

    /**
     * @param residential_address the residential_address to set
     */
    public void setResidential_address(String residential_address) {
        this.residential_address = residential_address;
    }

    /**
     * @return the residential_address_lga
     */
    public String getResidential_address_lga() {
        return residential_address_lga;
    }

    /**
     * @param residential_address_lga the residential_address_lga to set
     */
    public void setResidential_address_lga(String residential_address_lga) {
        this.residential_address_lga = residential_address_lga;
    }

    /**
     * @return the residential_address_state
     */
    public String getResidential_address_state() {
        return residential_address_state;
    }

    /**
     * @param residential_address_state the residential_address_state to set
     */
    public void setResidential_address_state(String residential_address_state) {
        this.residential_address_state = residential_address_state;
    }

    /**
     * @return the state_of_origin
     */
    public String getState_of_origin() {
        return state_of_origin;
    }

    /**
     * @param state_of_origin the state_of_origin to set
     */
    public void setState_of_origin(String state_of_origin) {
        this.state_of_origin = state_of_origin;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the custtable_id
     */
    public String getCusttable_id() {
        return custtable_id;
    }

    /**
     * @param custtable_id the custtable_id to set
     */
    public void setCusttable_id(String custtable_id) {
        this.custtable_id = custtable_id;
    }

    /**
     * @return the acct_name
     */
    public String getAcct_name() {
        return acct_name;
    }

    /**
     * @param acct_name the acct_name to set
     */
    public void setAcct_name(String acct_name) {
        this.acct_name = acct_name;
    }
    /**
     * @return the dateopen
     */
    public String getDateopen() {
        return dateopen;
    }

    /**
     * @param dateopen the dateopen to set
     */
    public void setDateopen(String dateopen) {
        this.dateopen = dateopen;
    }
    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the bvn
     */
    public String getBvn() {
        return bvn;
    }

    /**
     * @param bvn the bvn to set
     */
    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    /**
     * @return the full_name
     */
    public String getFull_name() {
        return full_name;
    }

    /**
     * @param full_name the full_name to set
     */
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the middlename
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * @param middlename the middlename to set
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     * @return the rcno
     */
    public String getRcno() {
        return rcno;
    }

    /**
     * @param rcno the rcno to set
     */
    public void setRcno(String rcno) {
        this.rcno = rcno;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the tin
     */
    public String getTin() {
        return tin;
    }

    /**
     * @param tin the tin to set
     */
    public void setTin(String tin) {
        this.tin = tin;
    }

    /**
     * @return the documents
     */
    public List<DocumentModel> getDocuments() {
        return documents;
    }

    /**
     * @param documents the documents to set
     */
    public void setDocuments(List<DocumentModel> documents) {
        this.documents = documents;
    }
}
