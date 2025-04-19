import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-account-specification',
  templateUrl: './account-specification.component.html',
  styleUrls: ['./account-specification.component.css']
})
export class AccountSpecificationComponent  implements OnInit{
  
  
  constructor(
    private router: Router,
    private apiService: ApiService,
    private storage: StorageService,
    private ngxService: NgxUiLoaderService
     ) {  }

  ngOnInit(): void {
    this.getAccountTypes();
    this.getEmployStat();
    this.getincomeBracketCode();
    this.getSectorList();
    this.getOccupationList();
    this.getBusinessList();
    
    if(JSON.parse(sessionStorage.getItem('branchList')!) !== null) {
      this.branchNames = JSON.parse(sessionStorage.getItem('branchList')!);
      if(JSON.parse(sessionStorage.getItem('selectedBranch')!) !== null) {
        this.selected_branch = JSON.parse(sessionStorage.getItem('selectedBranch')!);
      }
    } else {
      this.getBranchNames();
    };
    
    if(JSON.parse(sessionStorage.getItem('accountOfficerList')!) !== null) {
      this.accountOfficerNames = JSON.parse(sessionStorage.getItem('accountOfficerList')!);
      this.displayAccountOfficerList = true;
      if(JSON.parse(sessionStorage.getItem('selectedAccountOfficer')!) !== null) {
        this.seleceted_account_officer = JSON.parse(sessionStorage.getItem('selectedAccountOfficer')!);
      }
    };
    
    if(JSON.parse(sessionStorage.getItem('nextofkin')!) !== null) {
      this.nextofkin = JSON.parse(sessionStorage.getItem('nextofkin')!);
    };

    if(JSON.parse(sessionStorage.getItem('customer_info2')!) !== null) {
      this.customerInfo2  = JSON.parse(sessionStorage.getItem('customer_info2')!);
    };
    
    if(JSON.parse(sessionStorage.getItem('userpersonaldetails2')!) !== null) {
      this.userpersonaldetails2 = JSON.parse(sessionStorage.getItem('userpersonaldetails2')!);
    };
    
    if(JSON.parse(sessionStorage.getItem('selectedAccountType')!) !== null) {
      this.selected_accountType = JSON.parse(sessionStorage.getItem('selectedAccountType')!);
      this.selectedAccountType();
    }; 
  };
  
  getAccountTypes() {
    
    this.ngxService.startBackground('get-accounttypes');

    this.apiService.getAccountTypes().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);          
          this.ngxService.stopBackground('get-accounttypes');
          
          if(resp.statuscode == '00'){
            this.accountTypes = resp.data;
            sessionStorage.setItem('accountList', JSON.stringify(resp.data));
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
          this.ngxService.stopBackground('get-accounttypes');
          this.storage.global_error();
        }
      )
    });
  };

  selectedAccountType() {    
    let x = this.storage.getAccountTypeInfo(this.selected_accountType);
    // console.log(x);    
    if (((x[0])
      .acct_type
      .toLowerCase()).includes("corparate")) {
      this.displayCorporateCard = true
    } else {
      this.displayCorporateCard = false
    };
  };

  
  getEmployStat() {
    this.ngxService.startBackground('get-employStat');

    this.apiService.getEmployementStat().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);
          this.ngxService.stopBackground('get-employStat');

          if(resp.statuscode == '00'){
            this.employmentTypeList = resp.data;
            sessionStorage.setItem('employment', JSON.stringify(resp.data));
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
          this.ngxService.stopBackground('get-employStat');
          this.storage.global_error();
        }
      )
    });
  };

  getSectorList() {    
    this.ngxService.startBackground('get-sector');

    this.apiService.getSectorList().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);          
          this.ngxService.stopBackground('get-sector');

          if(resp.statuscode == '00'){
            this.sectorList = resp.data;
            sessionStorage.setItem('sectorList', JSON.stringify(resp.data));
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
          this.ngxService.stopBackground('get-sector');
          this.storage.global_error();
        }
      )
    });
  };

  getOccupationList() {

    this.ngxService.startBackground('get-occupation');

    this.apiService.getOccCode().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);
          this.ngxService.stopBackground('get-occupation');

          if(resp.statuscode == '00'){
            this.occupationList = resp.data;
            sessionStorage.setItem('occList', JSON.stringify(resp.data));
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
          this.ngxService.stopBackground('get-occupation');
          this.storage.global_error();
        }
      )
    });
  };

  getincomeBracketCode() {
    
    this.ngxService.startBackground('get-incomelist');

    this.apiService.getIncomeBracket().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);
          this.ngxService.stopBackground('get-incomelist');

          if(resp.statuscode == '00'){
            this.incomeBracketList = resp.data;
            sessionStorage.setItem('incomeBracketList', JSON.stringify(resp.data));
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
          this.ngxService.stopBackground('get-incomelist');
          this.storage.global_error();
        }
      )
    });
  };

  getBusinessList() {
    
    this.ngxService.startBackground('get-businesslist');

    this.apiService.getBusinessType().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);
          
          this.ngxService.stopBackground('get-businesslist');

          if(resp.statuscode == '00'){
            this.businessTypeList = resp.data;
            sessionStorage.setItem('businessList', JSON.stringify(resp.data));
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
          this.ngxService.stopBackground('get-businesslist');
          this.storage.global_error();
        }
      )
    });
  };

 
  getBranchNames() {
    
    this.ngxService.startBackground('get-branchlist');

    this.apiService.getBranchName().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);
          
          this.ngxService.stopBackground('get-branchlist');
          if(resp.statuscode == '00'){
            this.branchNames = resp.data;
            // this.storage.branchList = resp.data;
            sessionStorage.setItem('branchList', JSON.stringify(resp.data));
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
          this.ngxService.stopBackground('get-branchlist');
          this.storage.global_error();
        }
      )
    });
  };

  getAccountOfficers() {
    this.displayAccountOfficerList = false;
    
    let data: any = {
      branch_code: this.selected_branch
    };

    // console.log(data);
    
    this.ngxService.startBackground('get-accountofficerlist');

    this.apiService.getAccountOfficerName(data).subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);
          
          this.ngxService.stopBackground('get-accountofficerlist');

          if(resp.statuscode == '00'){
            this.accountOfficerNames = resp.data;
            this.displayAccountOfficerList = true;
            sessionStorage.setItem('accountOfficerList', JSON.stringify(resp.data));
          }
          else if(resp.statuscode == '96'){
            this.displayAccountOfficerList = false;
            this.storage.status_message_error(resp);
          }
          else{
            this.displayAccountOfficerList = false;
            this.storage.global_error();
          }
        }
        ),
        error: (
          err => {
          this.displayAccountOfficerList = false;
          this.ngxService.stopBackground('get-accountofficerlist');
          this.storage.global_error();
        }
      )
    });
  };

  next() {
    sessionStorage.setItem('nextofkin', JSON.stringify(this.nextofkin));
    sessionStorage.setItem('customer_info2', JSON.stringify(this.customerInfo2));
    sessionStorage.setItem('userpersonaldetails2', JSON.stringify(this.userpersonaldetails2));
    sessionStorage.setItem('selectedBranch', JSON.stringify(this.selected_branch));
    sessionStorage.setItem('selectedAccountType', JSON.stringify(this.selected_accountType));
    sessionStorage.setItem('selectedAccountOfficer', JSON.stringify(this.seleceted_account_officer));
    this.router.navigate(["/upload-document"]);
  };
  
  customerInfo2: any = {
    selected_sector: null,
    selected_occupation: null,
    selected_businessType: null,
    selected_income: null,
    selected_employment : null,
  }
  
  userpersonaldetails2: any = {
    tin: '',
    rcNumber: '',
  };

  nextofkin: any = {
    surname: '',
    firstname: '',
    phonenumber: '',
    email: '',
    relationship: '',
    residentialadd: ''
  };
  
  progress_bar: Boolean = false;
  displayCorporateCard: Boolean = false;
  displayIndividualCard: Boolean = false;
  displayAccountOfficerList: Boolean = false;  
  
  sectorList: any = [];
  branchNames: any = [];
  accountTypes: any = [];
  occupationList: any = [];
  businessTypeList: any = [];
  incomeBracketList: any = [];
  employmentTypeList: any = [];
  accountOfficerNames: any = [];  

  selected_branch: any = null;
  selected_accountType: any = null;  
  seleceted_account_officer: any = null;
}
