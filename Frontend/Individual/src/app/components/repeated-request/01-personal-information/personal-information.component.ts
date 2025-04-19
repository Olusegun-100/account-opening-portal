import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-personal-information',
  templateUrl: './personal-information.component.html',
  styleUrls: ['./personal-information.component.css']
})
export class PersonalInformationComponent {

  constructor(
    private router: Router,
    private api: ApiService,
    private storage: StorageService,
    private ngxservice: NgxUiLoaderService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder) {  }

    customerid: any = '';
  ngOnInit(): void {    
    this.getTitle();
    this.getNationality();
    this.getReligionList();
    this.getGender();
    this.getMaritalList();

    this.route.paramMap.subscribe((params: ParamMap) => {
      this.customerid = params.get('id');

      if(Object.keys(this.storage.rependingData).length < 1 || this.storage.rependingData < 1) {
        // sessionStorage.clear();
        this.getDataById(this.customerid)  
      } else {
        this.rependingData = this.storage.rependingData;
      }
    });
    
    if(JSON.parse(sessionStorage.getItem('titleList')!) !== null) {
      this.titleList = JSON.parse(sessionStorage.getItem('titleList')!);
    };
      
    // if(JSON.parse(sessionStorage.getItem('customer_info')!) !== null) {
    //   this.selected_title = JSON.parse(sessionStorage.getItem('customer_info')!);      
    //   // console.log(this.selected_accountType);
    //   this.selectedAccountTitle();
    // }

    if(JSON.parse(sessionStorage.getItem('branchList')!) !== null) {
      this.branchNames = JSON.parse(sessionStorage.getItem('branchList')!);
    }
    
    if(JSON.parse(sessionStorage.getItem('accountOfficerList')!) !== null) {
      this.accountOfficerNames = JSON.parse(sessionStorage.getItem('accountOfficerList')!);
      this.displayAccountOfficerList = true;
    };

  };

  getTitle() {
    this.progress_bar = true;
    this.api.gettitle().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };

  getMaritalList() {
    this.progress_bar = true;
    this.api.getMaritalStatus().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };
  
  getGender() {
    this.progress_bar = true;
    this.api.getgender().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };

  getReligionList() {
    this.progress_bar = true;
    this.api.getReligion().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };

  getNationality() {
    this.lgaList = [];
    this.stateNames = [];

    this.progress_bar = true;
    this.api.getNationality().subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  };

  getState() {
    this.progress_bar = true;

    this.lgaList = [];
    // this.customerInfo.selected_lga = null;
    // console.log(this.rependingData.nationality);
    
    
    let data: any = {
      country_code: this.rependingData.nationality
    };

    // console.log(data);    

    this.api.getState(data).subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  }

  getLga() {
    this.progress_bar = true;
    
    let data: any = {
      country_code: this.rependingData.nationality,
      state_code: this.rependingData.stateoforigin
    };

    // console.log(data);

    this.api.getLga(data).subscribe({
      next: (
        (resp: any) => {
          this.progress_bar = false;
          // console.log(resp);
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
          this.progress_bar = false;
          this.storage.global_error();
        }
      )
    });
  }; 

  getDataById(customerid: any) {
    let data = {
      id: customerid
    };
    this.ngxservice.start();
    this.api.getDetailsById(data).subscribe({
      next: (
        (res: any) => {
          // console.log(res);
          this.storage.timeOutNgxService();
          if (res.statuscode === "00") {
            this.storage.rependingData = res.data;
            this.rependingData = res.data;
            // console.log(this.rependingData);
            // console.log(this.rependingData.title);
            this.rependingData.dob = this.rependingData.dob.slice(0, 10)
            
            if(this.rependingData.nationality != null) {
              this.getState();
            }
            if(this.rependingData.stateoforigin != null) {
              this.getLga();
            }
            this.storage.rependingDocuments = res.data.document;
    
            this.getAccountTypes();
            this.getBranchNames();

            if(res.data.dob !== null || res.data.dob != 'undefined') {
            this.storage.rependingData.dob = res.data.dob.slice(0, 10);              
            };
            if(res.data.permitIssueDate !== null || res.data.permitIssueDate != 'undefined') {
              let a1 = res.data.permitIssueDate.slice(0, 10);
              let a2 = res.data.permitIssueDate.slice(11, 21);
              this.rependingData.permitIssueDate = a1 + 'T' + a2 + '00Z';
              // this.storage.rependingData.permitIssueDate = res.data.permitIssueDate.slice(0, 10);
            }; 
            if(res.data.permitExpiryDate !== null || res.data.permitExpiryDate != 'undefined') {
              let a3 = res.data.permitExpiryDate.slice(0, 10);
              let a4 = res.data.permitExpiryDate.slice(11, 21);
              this.rependingData.permitExpiryDate = a3 + 'T' + a4 + '00Z';
              // this.storage.rependingData.permitExpiryDate = res.data.permitExpiryDate.slice(0, 10);
            }                                 

            if(this.rependingData.branch_code != null || this.rependingData.branch_code != 'undefined' || this.rependingData.branch_code.length > 0) {
              this.getAccountOfficers();
              this.displayAccountOfficerList = true;
            } else {
              this.displayAccountOfficerList = false;
            }
            // console.log(this.rependingData);
          }
          else if(res.statuscode === "96") {
            this.isLoading = false;
            this.storage.status_message_error(res);
          }
          else {
            this.isLoading = false;
            this.storage.global_error();
          }          
        }
      ),
      error: (
        err => {
          this.storage.timeOutNgxService();
          this.storage.global_error();
        }
      )
    }); 
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

  // selectedAccountTitle() {
  //   this.selected_title = this.rependingData.title;
  //   let x = this.storage.getTitleInfo(this.rependingData.title);
  //   console.log(x);
  // }

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
            // this.selectedAccountTitle();
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
  
  changeAccountOfficer() {
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
  }

  next() {
    this.storage.rependingData = this.rependingData;
    
    // sessionStorage.setItem('selectedAccountTypeRP', JSON.stringify(this.selected_accountType));
    // sessionStorage.setItem('accountTypeRP', JSON.stringify(this.accountType));
    
    if(this.customerid.length > 0) {
      sessionStorage.setItem('customer', this.customerid);
    };  
    this.router.navigate(["/r/account-specification"]);
  };

  rependingData: any = [];

  isLoading: Boolean = false;

  displayAccountOfficerList: Boolean = false;
  displayCorporateCard: Boolean = false;
  
  selected_accountType: any = null;  

  branchNames: any = [];
  selected_title: any = null;
  accountOfficerNames: any = [];
  selected_account_officer: any = null;

  progress_bar: Boolean = false;
  accountTypes: any = [];
  titleList: any = [];
  genderList:any = [];
  maritalList:any = [];
  religionList:any = [];
  nationalities:any = [];
  stateNames:any = [];
  lgaList:any = [];
  // accountType: any = null;

  bankBranchs: any = [];
  bankBranch: any = null;

  accountOfficers: any = [];
  accountOfficer: any = null;
 

}
