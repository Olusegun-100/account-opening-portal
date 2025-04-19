import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
// import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

//   baseUrl: string = "http://localhost:1000/api/";
  baseUrl: string = "http://app.itmbplc.com:1999/";
  // baseUrl: string = "https://suntrustdigital.herokuapp.com/https://app.itmbplc.com:8443/";
  baseUrl2: string = "http://app.infinitytrustmortgagebank.com:8080/";
  
  verifyBvn(data: any){
    let url= this.baseUrl + "BvnEndpoint/webresources/details/bvn?bvn=" + data.bvn;
    // console.log(url);    
    return this.http.get(url);
  }

  getAccountTypes(){
    let url= this.baseUrl2 + "GetAccountTypes/webresources/getaccttypes"
    // console.log(url);    
    return this.http.get(url);
  }

  getCorporateAccountTypes(){
    let url= this.baseUrl2 + "CorparateAcctTypes/webresources/corparateAcct"
    // console.log(url);    
    return this.http.get(url);
  }

  getNationality(){
    let url= this.baseUrl2 + "Nationality/webresources/nationality"
    // console.log(url);    
    return this.http.get(url);
  }

  getEmployementStat(){
    let url= this.baseUrl2 + "EmployStatus/webresources/employementStat"
    // console.log(url);    
    return this.http.get(url);
  }

  getIncomeBracket(){
    let url= this.baseUrl2 + "IncomeBracket/webresources/incomebracket"
    // console.log(url);    
    return this.http.get(url);
  }

  getSectorList(){
    let url= this.baseUrl2 + "SectorCode/webresources/sectorcode"
    // console.log(url);    
    return this.http.get(url);
  }

  getOccCode(){
    let url= this.baseUrl2 + "OccCode/webresources/occCode"
    // console.log(url);    
    return this.http.get(url);
  }

  getBusinessType(){
    let url= this.baseUrl2 + "BusinessType/webresources/businessType"
    // console.log(url);    
    return this.http.get(url);
  }

  getReligion(){
    let url= this.baseUrl2 + "Religion/webresources/religion"
    // console.log(url);    
    return this.http.get(url);
  }

  getMaritalStatus(){
    let url= this.baseUrl2 + "MariatalStatus/webresources/maritalStat"
    // console.log(url);    
    return this.http.get(url);
  }

  getState(data:any){
    let url= this.baseUrl2 + "StateOfOrigin/webresources/state"
    // console.log(url);    
    return this.http.post(url,data);
  }

  getLga(data:any) {
    let url= this.baseUrl2 + "Lga/webresources/lga"
    // console.log(url);    
    return this.http.post(url,data);
  }

  getgender(){
    let url= this.baseUrl2 + "Gender/webresources/gender"
    // console.log(url);    
    return this.http.get(url);
  }

  gettitle(){
    let url= this.baseUrl2 + "Title/webresources/title"
    // console.log(url);
    return this.http.get(url);
  }

  getAgentNames(){
    let url= this.baseUrl2 + "AgentName/webresources/agent"
    // console.log(url);    
    return this.http.get(url);
  }

  getBranchName(){
    let url= this.baseUrl2 + "GetBranch/webresources/branches"
    // console.log(url);    
    return this.http.get(url);
  }

  getAccountOfficerName(data:any){
    let url= this.baseUrl2 + "GetOfficerByBranch/webresources/accountOfficer"
    // console.log(url);    
    return this.http.post(url,data);
  }

  sendOtp(data: any){
    let url= this.baseUrl2 + "OtpConsumption/webresources/otp/send"
    // console.log(url);    
    return this.http.post(url, data);
  }

  verifyOtp(data: any){
    let url= this.baseUrl2 + "OtpConsumption/webresources/otp/verify"
    // console.log(url);    
    return this.http.post(url, data);
  }

  getDetailsById(data: any){
    let url= this.baseUrl2 + "FullAccountDetails/webresources/getdetails";
    // console.log(url);    
    return this.http.post(url, data);
  }

  submit(data: any){
    let url= //this.baseUrl + 
    this.baseUrl2 + "AccountOpx/webresources/accountopening";
    // console.log(url);    
    return this.http.post(url, data);
  }

  re_submit(data: any){
    let url= this.baseUrl2 + "UpdateCustomerDetails/webresources/updatedetails";
    // console.log(url);    
    return this.http.post(url, data);
  }

  submitCorp(data: any){
    let url= //this.baseUrl + 
    this.baseUrl2 + "AccountOpxCorp/webresources/accountopening";
    // console.log(url);    
    return this.http.post(url, data);
  }

  re_submitCorp(data: any){
    let url= this.baseUrl2 + "UpdateCustomerDetails/webresources/updatedetails";
    // console.log(url);    
    return this.http.post(url, data);
  }


}