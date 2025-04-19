import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-personal-information',
  templateUrl: './personal-information.component.html',
  styleUrls: ['./personal-information.component.css']
})
export class PersonalInformationComponent implements OnInit {

  constructor(
    private router: Router,
    private apiService: ApiService,
    private storage: StorageService,
    private ngxService: NgxUiLoaderService
     ) {  }

    
  ngOnInit(): void {
    this.getTitle();
    this.getGender();
    this.getReligionList();
    this.getMaritalList();
    this.getNationality();

    if(JSON.parse(sessionStorage.getItem('bvn_info')!) !== null) {
      this.personaldetails = JSON.parse(sessionStorage.getItem('bvn_info')!);
      // console.log(this.personaldetails);      
      this.personaldetails.email.length > 0 ? this.isEmailAvailable = true : this.isEmailAvailable = false;
    };
    
    if(JSON.parse(sessionStorage.getItem('customer_info')!) !== null) {
      this.customerInfo  = JSON.parse(sessionStorage.getItem('customer_info')!);
      if(this.customerInfo.selected_nationality != null) {
        this.getState();
      };
      if(this.customerInfo.selected_state != null) {
        this.getLga();
      };
    };

    if(JSON.parse(sessionStorage.getItem('userpersonaldetails')!) !== null) {
      this.userpersonaldetails = JSON.parse(sessionStorage.getItem('userpersonaldetails')!);
      // console.log(this.userpersonaldetails);      
    };
 
  };

  getNationality() {
    this.lgaList = [];
    this.stateNames = [];
    this.customerInfo.selected_state = null;
    this.customerInfo.selected_lga = null;

    this.ngxService.startBackground('get-nationality');
    
    this.apiService.getNationality().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);      
          this.ngxService.stopBackground('get-nationality');

          if(resp.statuscode == '00'){
            this.nationalities = resp.data;
            sessionStorage.setItem('nationalityList', JSON.stringify(resp.data));
          }
          else if(resp.statuscode == '96'){
            this.storage.status_message_error(resp);
          }
          else{
            this.storage.global_error();
          }
        }
      ),
      error: (
        err => {
          this.ngxService.stopBackground('get-nationality');
          this.storage.global_error();
        }
      )
    });
  };

  getState() {
    this.progress_bar = true;

    this.lgaList = [];
    // this.customerInfo.selected_lga = null;
    
    let data: any = {
      country_code: this.customerInfo.selected_nationality
    };
    // console.log(data);
    
    this.ngxService.startBackground('get-state');

    this.apiService.getState(data).subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);
          
          this.ngxService.stopBackground('get-state');
          if(resp.statuscode == '00'){
            this.stateNames = resp.data;
            sessionStorage.setItem('stateList', JSON.stringify(resp.data));
          }
          else if(resp.statuscode == '96'){
            this.storage.status_message_error(resp);
          }
          else{
            this.storage.global_error();
          }
        }
        ),
        error: (
          err => {
          this.ngxService.stopBackground('get-state');
          this.storage.global_error();
        }
      )
    });
  }

  getLga() {
    
    let data: any = {
      country_code: this.customerInfo.selected_nationality,
      state_code: this.customerInfo.selected_state
    };
    // console.log(data);
    
    this.ngxService.startBackground('get-lga');

    this.apiService.getLga(data).subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);
          this.ngxService.stopBackground('get-lga');

          if(resp.statuscode == '00'){
            this.lgaList = resp.data;
            sessionStorage.setItem('lgaList', JSON.stringify(resp.data));
          }
          else if(resp.statuscode == '96'){
            this.storage.status_message_error(resp);
          }
          else{
            this.storage.global_error();
          }
        }
        ),
        error: (
          err => {            
          this.ngxService.stopBackground('get-lga');
          this.storage.global_error();
        }
      )
    });
  }; 

  getReligionList() {
    
    this.ngxService.startBackground('get-religion');

    this.apiService.getReligion().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
          
          this.ngxService.stopBackground('get-religion');
          if(resp.statuscode == '00'){
            this.religionList = resp.data;
            sessionStorage.setItem('religionList', JSON.stringify(resp.data));
          }
          else if(resp.statuscode == '96'){
            this.storage.status_message_error(resp);
          }
          else{
            this.storage.global_error();
          }
        }
      ),
      error: (
        err => {
          this.ngxService.stopBackground('get-religion');
          this.storage.global_error();
        }
      )
    });
  };

  getMaritalList() {    
    this.ngxService.startBackground('get-maritalStatus');

    this.apiService.getMaritalStatus().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);          
          this.ngxService.stopBackground('get-maritalStatus');
          if(resp.statuscode == '00'){
            this.maritalList = resp.data;
            sessionStorage.setItem('maritalList', JSON.stringify(resp.data));
          }
          else if(resp.statuscode == '96'){
            this.storage.status_message_error(resp);
          }
          else{
            this.storage.global_error();
          }
        }
      ),
      error: (
        err => {
          this.ngxService.stopBackground('get-maritalStatus');
          this.storage.global_error();
        }
      )
    });
  };

  getGender() {  
    this.ngxService.startBackground('get-gender');
    this.apiService.getgender().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);          
          this.ngxService.stopBackground('get-gender');

          if(resp.statuscode == '00'){
            this.genderList = resp.data;
            sessionStorage.setItem('genderList', JSON.stringify(resp.data));
          }
          else if(resp.statuscode == '96'){
            this.storage.status_message_error(resp);
          }
          else{
            this.storage.global_error();
          }
        }
      ),
      error: (
        err => {          
          this.ngxService.stopBackground('get-gender');
          this.storage.global_error();
        }
      )
    });
  };

  getTitle() {
    this.ngxService.startBackground('get-title');
    this.apiService.gettitle().subscribe({
      next: (
        (resp: any) => {          
          // console.log(resp);
          this.ngxService.stopBackground('get-title');

          if(resp.statuscode == '00'){
            this.titleList = resp.data;
            sessionStorage.setItem('titleList', JSON.stringify(resp.data));
          }
          else if(resp.statuscode == '96'){
            this.storage.status_message_error(resp);
          }
          else{
            this.storage.global_error();
          }
        }
      ),
      error: (
        err => {
          this.ngxService.stopBackground('get-title');
          this.storage.global_error();
        }
      )
    });
  };

  next() {
    sessionStorage.setItem('customer_info', JSON.stringify(this.customerInfo));
    sessionStorage.setItem('bvn_info', JSON.stringify(this.personaldetails));
    sessionStorage.setItem('userpersonaldetails', JSON.stringify(this.userpersonaldetails));
    this.router.navigate(["/account-specification"]);
  };
  
  customerInfo: any = {
    selected_religion: null,
    selected_maritalStatus : null,
    selected_gender : null,
    selected_title : null,
    selected_lga: null,
    selected_state: null,
    selected_nationality: null,
  };

  personaldetails: any = {
  };

  userpersonaldetails: any = {
    mothersmaidenname: '',
    placeofbirth: '',
    residentpermitnumber: '',
    placeofpermitissue: '',
    permitissuedate: '',
    permitexpirydate: '',
  };
  
  lgaList: any = [];
  titleList: any = [];
  genderList: any = [];
  stateNames: any = [];
  maritalList: any = [];
  religionList: any = [];
  nationalities: any = [];
  
  progress_bar: Boolean = false;
  isEmailAvailable: Boolean = false;
};