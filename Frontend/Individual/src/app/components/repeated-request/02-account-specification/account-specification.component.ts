import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';
@Component({
  selector: 'app-account-specification',
  templateUrl: './account-specification.component.html',
  styleUrls: ['./account-specification.component.css']
})
export class AccountSpecificationComponent {


  constructor(
    private router: Router,
    private api: ApiService,
    private storage: StorageService,
    private ngxservice: NgxUiLoaderService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder) {  }

    customerid: any = '';

    
  ngOnInit(): void {    
    // this.routeTo = this.storage.rependingCustomerId;

    if(sessionStorage.getItem('customer') !== null) {
      this.routeTo = sessionStorage.getItem('customer');
    };    
    // this.rependingData = this.storage.rependingData;

    if(Object.keys(this.storage.rependingData).length < 1) {
      this.router.navigate(['/r/personal-information/' + this.routeTo]);
    } else {
      this.rependingData = this.storage.rependingData;
      
      this.getBusinessList();
      this.getincomeBracketCode();
      this.getOccupationList();
      this.getBusinessList();
      this.getSectorList();
      this.getEmployStat();
      
      if(JSON.parse(sessionStorage.getItem('accountList')!) !== null) {
        this.accountTypes = JSON.parse(sessionStorage.getItem('accountList')!);
        // console.log(this.accountTypes);
        
        // this.selectedAccountType();
        if(JSON.parse(sessionStorage.getItem('selectedAccountType')!) !== null) {
          this.selected_accountType = JSON.parse(sessionStorage.getItem('selectedAccountType')!);      
          // console.log(this.selected_accountType)
          this.selectedAccountType();
        }
      };
  
      if(JSON.parse(sessionStorage.getItem('branchList')!) !== null) {
        this.branchNames = JSON.parse(sessionStorage.getItem('branchList')!);
      }
      
      if(JSON.parse(sessionStorage.getItem('accountOfficerList')!) !== null) {
        this.accountOfficerNames = JSON.parse(sessionStorage.getItem('accountOfficerList')!);
        this.displayAccountOfficerList = true;
      };
    }

  };
  
  selectedAccountType() {

    this.selected_accountType = this.rependingData.accttypes;
    let x = this.storage.getAccountTypeInfo(this.rependingData.accttypes);
    // console.log(x);

    if(x.length < 1)
     return;
    else if (((x[0]).acct_type.toLowerCase()).includes("corporate")) {
      // console.log('true');      
      return  this.displayCorporateCard = true;
    } else {
      // console.log('false');
      return this.displayCorporateCard = false;
    }
  }

  getAccountTypes() {
    this.progress_bar = true;
    this.api.getAccountTypes().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
          if(resp.statuscode == '00'){
            this.accountTypes = resp.data;
            // console.log(this.accountTypes);
            
            sessionStorage.setItem('accountList', JSON.stringify(resp.data));
            this.storage.accountList = resp.data;
            this.selectedAccountType();
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };
  
  getBranchNames() {
    this.progress_bar = true;
    this.api.getBranchName().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  }
  
  changeAccountOfficersList() {
    this.rependingData.officerName = null;
    this.getAccountOfficers();
  }

  getAccountOfficers() {
    this.progress_bar = true;
    this.displayAccountOfficerList = false;
    
    let data: any = {
      branch_code: this.rependingData.branch_code
    };

    // console.log(data);    

    this.api.getAccountOfficerName(data).subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
          if(resp.statuscode == '00'){
            // this.rependingData.officerName = null;
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };

  getEmployStat() {
    this.progress_bar = true;
    this.api.getEmployementStat().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };

  getSectorList() {
    this.progress_bar = true;
    this.api.getSectorList().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };

  getOccupationList() {
    this.progress_bar = true;
    this.api.getOccCode().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };

  getincomeBracketCode() {
    this.progress_bar = true;
    this.api.getIncomeBracket().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };  

  getBusinessList() {
    this.progress_bar = true;
    this.api.getBusinessType().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };

  routeTo: any = '';

  back() {    
    this.router.navigate(['/r/personal-information/' + this.routeTo]);
  }
  next() {
    this.storage.rependingData = this.rependingData;
    
    // sessionStorage.setItem('selectedAccountTypeRP', JSON.stringify(this.selected_accountType));
    // sessionStorage.setItem('accountTypeRP', JSON.stringify(this.accountType));
    this.router.navigate(["r/upload-document"]);
  }

  rependingData: any = [];

  // personaldetails: any = {
  // }

  // userpersonaldetails: any = {}

  // addressdetails: any = {}

  // meansofidentification: any = {}

  // nextofkin: any = {}

  // employmentdetails: any = {}

  // accountspecification: any = {};
  
  isLoading: Boolean = false;

  displayAccountOfficerList: Boolean = false;
  displayCorporateCard: Boolean = false;
  
  selected_accountType: any = null;  

  branchNames: any = [];
  selected_branch: any = null;
  accountOfficerNames: any = [];
  selected_account_officer: any = null;

  progress_bar: Boolean = false;
  accountTypes: any = [];
  businessTypeList:any = [];
  occupationList:any = [];
  sectorList:any = [];
  incomeBracketList:any = [];
  employmentTypeList:any = [];
  // accountType: any = null;

  bankBranchs: any = [];
  bankBranch: any = null;

  accountOfficers: any = [];
  accountOfficer: any = null;

}