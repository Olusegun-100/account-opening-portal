import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from './storage.service';
import { map, shareReplay } from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) { }

  data: any = [];

  private loggedInStatus = JSON.parse(sessionStorage.getItem('loggedIn') || 'false');

  setLoggedIn(value: boolean){
    this.loggedInStatus = value;
    sessionStorage.setItem('loggedIn', 'true');
  }

  setLoggedOut(value: boolean){
    this.loggedInStatus = value
    sessionStorage.setItem('loggedIn', 'false')
  }

  get isLoggedIn(){
    return !!(sessionStorage.getItem('loggedIn') || this.loggedInStatus)
  }

  // private headers(){
  //   let encodedData = window.btoa('gims:gims');
  //   // console.log(encodedData);

  //   let headers = new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     'Authorization': `Bearer ${encodedData}`
  //   });
  //   return headers;
  // }

  baseUrl: any = 'http://localhost:1997/' //new
  // baseUrl: string = "http://app.infinitytrustmortgagebank.com:8080/";

  getUserDetails(data: any){
    // AdminAccountOpening/webresources/adminportallogin
    let url = this.baseUrl + 'AdminAccountOpening/webresources/adminportallogin';
    console.log(url);

    return this.http.post(url,data)
    // .pipe(
    //   map(response => response),
    //   // retry(3),
    //   shareReplay());
  }

  logout(){
    this.router.navigate(['login'])
    this.setLoggedOut(false);
    sessionStorage.clear();
    Swal.close();
  }
}
