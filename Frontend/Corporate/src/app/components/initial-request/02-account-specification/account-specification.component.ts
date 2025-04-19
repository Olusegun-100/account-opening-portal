import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, FormArray } from '@angular/forms';

import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';

import Swal from 'sweetalert2';

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
    private ngxService: NgxUiLoaderService,
    private formBuilder: FormBuilder) {
      // this.formGroup = this.formBuilder.group({
      //   formArray: this.formBuilder.array([])
      // });
    }

  ngOnInit(): void {

    this.getStoredAccountServices()

    this.getAccountTypes();
    this.getState();
    this.getincomeBracketCode();
    this.getSectorList();
    this.getOccupationList();
    this.getBusinessList();
    
    this.getAgentNames();
    
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
        this.selected_account_officer = JSON.parse(sessionStorage.getItem('selectedAccountOfficer')!);
      }
    };

    if(JSON.parse(sessionStorage.getItem('customer_info')!) !== null) {
      this.customerInfo  = JSON.parse(sessionStorage.getItem('customer_info')!);

      if(this.customerInfo.selected_state != null ) {
        this.getLga();
      }
    };
    
    if(JSON.parse(sessionStorage.getItem('selectedAccountType')!) !== null) {
      this.selected_accountType = JSON.parse(sessionStorage.getItem('selectedAccountType')!);
      // this.selectedAccountType();
    }; 
    
    if(JSON.parse(sessionStorage.getItem('selectedAgent')!) !== null) {
      this.selected_agent = JSON.parse(sessionStorage.getItem('selectedAgent')!);
    }; 
    
    if(JSON.parse(sessionStorage.getItem('selectedAccountOfficer')!) !== null) {
      this.selected_account_officer = JSON.parse(sessionStorage.getItem('selectedAccountOfficer')!);
    }; 
  };

  getStoredAccountServices() {
    const storedValues = sessionStorage.getItem('account_services');
    if (storedValues) {
      const selectedValues = storedValues.split(',');
      this.account_services.forEach(item => {
        item.checked = selectedValues.includes(item.name);
      });
    };
  }

  stockExchange() {    
    if(this.customerInfo.stockExchange == 'no') {
      this.customerInfo.stockExchangeSymbol = '';
    }
  };

  getAccountTypes() {
    
    this.ngxService.startBackground('account-types');

    this.apiService.getCorporateAccountTypes().subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);          
          this.ngxService.stopBackground('account-types');

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
          this.ngxService.stopBackground('account-types');
          this.storage.global_error();
        }
      )
    });
  };

  // selectedAccountType() {    
  //   let x = this.storage.getAccountTypeInfo(this.selected_accountType);
  //   console.log(x);    
  //   if (((x[0])
  //     .acct_type
  //     .toLowerCase()).includes("corporate")) {
  //     this.displayCorporateCard = true
  //   } else {
  //     this.displayCorporateCard = false
  //   };
  // };

  getSectorList() {
    
    this.ngxService.startBackground('selected-list');

    this.apiService.getSectorList().subscribe({
      next: (
        (resp: any) => {
          
          this.ngxService.stopBackground('selected-list');
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
          this.ngxService.stopBackground('selected-list');
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
          this.ngxService.stopBackground('get-occupation');
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
          this.ngxService.stopBackground('get-occupation');
          this.storage.global_error();
        }
      )
    });
  };

  getincomeBracketCode() {
    this.ngxService.startBackground('get-income-bracket');
    this.apiService.getIncomeBracket().subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-income-bracket');
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
          this.ngxService.stopBackground('get-income-bracket');
          this.storage.global_error();
        }
      )
    });
  };

  getBusinessList() {
    this.ngxService.startBackground('get-business-list');

    this.apiService.getBusinessType().subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-business-list');
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
          this.ngxService.stopBackground('get-business-list');
          this.storage.global_error();
        }
      )
    });
  };
 
  getBranchNames() {
    
    this.ngxService.startBackground('get-branch-names');

    this.apiService.getBranchName().subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-branch-names');

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
          this.ngxService.stopBackground('get-branch-names');
          this.storage.global_error();
        }
      )
    });
  };
 
  getAgentNames() {
    this.ngxService.startBackground('get-agent-names');

    this.apiService.getAgentNames().subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-agent-names');
          // console.log(resp);
          if(resp.statuscode == '00'){
            this.agentNames = resp.data;
            // this.storage.branchList = resp.data;
            // sessionStorage.setItem('branchList', JSON.stringify(resp.data));
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
          this.ngxService.stopBackground('get-agent-names');
          this.storage.global_error();
        }
      )
    });
  };

  getAccountOfficers() {
    
    this.ngxService.startBackground('get-account-officers');

    this.displayAccountOfficerList = false;
    
    let data: any = {
      branch_code: this.selected_branch
    };

    this.apiService.getAccountOfficerName(data).subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-account-officers');

          // console.log(resp);
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
          this.ngxService.stopBackground('get-account-officers');
          this.storage.global_error();
        }
      )
    });
  };
  
  getState() {
    this.ngxService.startBackground('get-state');

    this.lgaList = [];
    // this.customerInfo.selected_lga = null;
    
    let data: any = {
      country_code: 'NGR'
    };  

    this.apiService.getState(data).subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-state');
          // console.log(resp);
          if(resp.statuscode == '00'){
            this.stateList = resp.data;
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
    this.ngxService.startBackground('get-lga');
    
    let data: any = {
      country_code: 'NGR',
      state_code: this.customerInfo.selected_state
    };

    this.apiService.getLga(data).subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-lga');
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
            this.ngxService.stopBackground('get-lga');
            this.storage.global_error();
          }
        )
      });
  };

  saveSelectedAccountServices(): void {
    const selected: any = this.account_services
      .filter(x => x.checked)
      .map(x => x.name);

    const serialized: string = selected.join(',');
    // console.log(serialized);
    
    
    sessionStorage.setItem('account_services', serialized)
  }


  next() {
  if(this.customerInfo.dateOfRegistration == null) {
    Swal.fire({
      // html: `${res.statusmessage.toUpperCase()}`,
      html: `Please a Date of Registration!`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else if(this.customerInfo.selected_businessType == null) {
    Swal.fire({
      html: `Please a Business Type!`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else if(this.customerInfo.selected_sector == null) {
    Swal.fire({
      html: `Please a Sector!`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else if(this.customerInfo.selected_state == null) {
    Swal.fire({
      html: `Please a State!`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else if(this.customerInfo.selected_lga == null) {
    Swal.fire({
      html: `Please a Local Government!`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else if(this.customerInfo.selected_income == null) {
    Swal.fire({
      html: `Please an Estimated Annual Turnover!`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else if(this.customerInfo.stockExchange == null) {
    Swal.fire({
      html: `Is your company quoted on any Stock Exchange?`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else if(this.selected_accountType == null) {
    Swal.fire({
      html: `Please an Account Type!`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else if(this.selected_branch == null) {
    Swal.fire({
      html: `Please select a Branch!`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else if(this.selected_account_officer == null) {
    Swal.fire({
      html: `Please an Account Officer!`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else if(this.selected_agent == null) {
    Swal.fire({
      // html: `${res.statusmessage.toUpperCase()}`,
      html: `Please select an Agent!!`,
      icon: 'warning',
      customClass: 'swal-wide',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    });
  } else {
    this.saveSelectedAccountServices();
    
    sessionStorage.setItem('selectedBranch', JSON.stringify(this.selected_branch));
    sessionStorage.setItem('selectedAgent', JSON.stringify(this.selected_agent));
    sessionStorage.setItem('selectedAccountType', JSON.stringify(this.selected_accountType));
    sessionStorage.setItem('selectedAccountOfficer', JSON.stringify(this.selected_account_officer));
    
    sessionStorage.setItem('customer_info', JSON.stringify(this.customerInfo));
    
    this.router.navigate(["/account-signatory"]);


  }

    
  }; 
  
  imageLogo: any = this.storage.imageLogo;

  customerInfo: any = {
    companyname: '',
    dateOfRegistration: null,
    jurisdictionOfRegistration: '',
    operatingAddress1: '',
    operatingAddress2: '',
    emailAddress: '',
    businessAddress: '',
    crm: '',
    scuml: '',
    website: '',
    phonenumber: '',
    stockExchange: null,
    stockExchangeSymbol: '',
    selected_state: null,
    selected_lga: null,
    selected_sector: null,
    selected_businessType: null,
    selected_income: null,
    tin: '',
    rcNumber: '',
  };

  selected_account_services: any = [];

  account_services: { id: number, name: string, checked: boolean }[]  = [
    {
      id: 1,
      name: 'E-mail Statement',
      checked: false
    },
    {
      id: 2,
      name: 'E-mail Alert',
      checked: false
    },
    {
      id: 3,
      name: 'Naira Debit Card',
      checked: false
    },
    {
      id: 4,
      name: 'Sms Alert (charges apply)',
      checked: false
    },
    {
      id: 5,
      name: 'Mobile Banking',
      checked: false
    },
    {
      id: 6,
      name: 'Internet Banking',
      checked: false
    },
    {
      id: 7,
      name: 'Cheque Confirmation',
      checked: false
    },
  ];


  lgaList: any = [];
  stateList: any = [];
  
  progress_bar: Boolean = false;
  displayCorporateCard: Boolean = false;
  displayIndividualCard: Boolean = false;
  displayAccountOfficerList: Boolean = false;  
  
  sectorList: any = [];
  branchNames: any = [];
  agentNames: any = [];
  accountTypes: any = [];
  occupationList: any = [];
  businessTypeList: any = [];
  incomeBracketList: any = [];
  employmentTypeList: any = [];
  accountOfficerNames: any = [];  

  selected_agent: any = null;
  selected_branch: any = null;
  selected_accountType: any = null;  
  selected_account_officer: any = null;

};