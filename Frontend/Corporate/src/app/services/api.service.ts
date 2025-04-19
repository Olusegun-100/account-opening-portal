import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
// import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  // baseUrl: string = 'http://localhost:1997/';
  baseUrl: string = "http://app.itmbplc.com:1999/";
  baseUrl2: string = "http://app.infinitytrustmortgagebank.com:8080/";

  // baseUrl2: string = "https://itmb-digital.herokuapp.com/http://app.infinitytrustmortgagebank.com:8080/";
  // baseUrl2: string = "https://suntrustdigital.herokuapp.com/http://app.infinitytrustmortgagebank.com:8080/";
  
  verifyBvn(data: any){
    let url= this.baseUrl + "BvnEndpoint/webresources/details/bvn?bvn=" + data.bvn;
    console.log(url);
    return this.http.get(url);
  }

  sendOtp(data: any){
    let url= this.baseUrl2 + "OtpConsumption/webresources/otp/send"    
    return this.http.post(url, data);
  }

  verifyOtp(data: any){
    let url= this.baseUrl2 + "OtpConsumption/webresources/otp/verify"    
    return this.http.post(url, data);
  };
  getAccountTypes(){
    let url= this.baseUrl2 + "GetAccountTypes/webresources/getaccttypes"    
    return this.http.get(url);
  }

  getCorporateAccountTypes(){
    let url= this.baseUrl2 + "CorparateAcctTypes/webresources/corparateAcct"    
    return this.http.get(url);
  }

  getNationality(){
    let url= this.baseUrl2 + "Nationality/webresources/nationality"    
    return this.http.get(url);
  }

  getEmployementStat(){
    let url= this.baseUrl2 + "EmployStatus/webresources/employementStat"    
    return this.http.get(url);
  }

  getIncomeBracket(){
    let url= this.baseUrl2 + "IncomeBracket/webresources/incomebracket"    
    return this.http.get(url);
  }

  getSectorList(){
    let url= this.baseUrl2 + "SectorCode/webresources/sectorcode"    
    return this.http.get(url);
  }

  getOccCode(){
    let url= this.baseUrl2 + "OccCode/webresources/occCode"    
    return this.http.get(url);
  }

  getBusinessType(){
    let url= this.baseUrl2 + "BusinessType/webresources/businessType"    
    return this.http.get(url);
  }

  getReligion(){
    let url= this.baseUrl2 + "Religion/webresources/religion"    
    return this.http.get(url);
  }

  getMaritalStatus(){
    let url= this.baseUrl2 + "MariatalStatus/webresources/maritalStat"    
    return this.http.get(url);
  }

  getState(data:any){
    let url= this.baseUrl2 + "StateOfOrigin/webresources/state"    
    return this.http.post(url,data);
  }

  getLga(data:any) {
    let url= this.baseUrl2 + "Lga/webresources/lga"    
    return this.http.post(url,data);
  }

  getgender(){
    let url= this.baseUrl2 + "Gender/webresources/gender"    
    return this.http.get(url);
  }

  gettitle(){
    let url= this.baseUrl2 + "Title/webresources/title"
    return this.http.get(url);
  }

  getAgentNames(){
    let url= this.baseUrl2 + "AgentName/webresources/agent"    
    return this.http.get(url);
  }

  getBranchName(){
    let url= this.baseUrl2 + "GetBranch/webresources/branches"    
    return this.http.get(url);
  }

  getAccountOfficerName(data:any){
    let url= this.baseUrl2 + "GetOfficerByBranch/webresources/accountOfficer"    
    return this.http.post(url,data);
  }

  getDetailsById(data: any){
    let url= this.baseUrl2 + "FullAccountDetails/webresources/getdetails";    
    return this.http.post(url, data);
  }

  submit(data: any){
    let url= //this.baseUrl + 
    this.baseUrl2 + "AccountOpx/webresources/accountopening";    
    return this.http.post(url, data);
  }

  re_submit(data: any){
    let url= this.baseUrl2 + "UpdateCustomerDetails/webresources/updatedetails";    
    return this.http.post(url, data);
  }

  submitCorp(data: any){
    let url= //this.baseUrl + 
    this.baseUrl2 + "AccountOpxCorp/webresources/accountopening";    
    return this.http.post(url, data);
  }

  re_submitCorp(data: any){
    let url= this.baseUrl2 + "UpdateCustomerDetailsCorparate/webresources/updatedetailscorp";    
    return this.http.post(url, data);
  }


}