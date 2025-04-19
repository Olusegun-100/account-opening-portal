import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
// import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }
baseUrl: string = 'http://localhost:1997/';
// baseUrl: string = "http://app.infinitytrustmortgagebank.com:8080/";
// baseUrl: string = 'https://suntrustdigital.herokuapp.com/http://localhost:1997';

private headers() {
  let header = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  return header;
}

getCount(data: any) {
    let url= this.baseUrl + "GetAccountStatusCounts/webresources/getCounts";
    return this.http.post(url, data)  
}

getAccountTable(data: any) {
    let url= this.baseUrl + "GetAccountTableByID/webresources/GetAccountTable";  
    return this.http.post(url, data, 
      // { headers: this.headers() }
    );
}

getAccountTableByDate(data: any) {
    let url= this.baseUrl + "DisplayAccountbyDate/webresources/filterbydate";
    // console.log(url);    
    return this.http.post(url, data, 
      // { headers: this.headers() }
    );
}

getFullDetails(data: any) {
    let url= this.baseUrl + "FullAccountDetails/webresources/getdetails";
    return this.http.post(url,data)
}


resubmission (data: any) {
  // "update/webresources/updatedetails"  
  let url = this.baseUrl + "update/webresources/updatedetails";
  console.log("This-----" +url);
  
}

approveOrReject(data: any) {
    let url= this.baseUrl + "RequestApprovalorRejection/webresources/approveOreject";
    return this.http.put(url,data)
}


getAccountOfficerName(data:any){
  let url= this.baseUrl + "GetOfficerByBranch/webresources/accountOfficer";
  return this.http.post(url,data);
}



}