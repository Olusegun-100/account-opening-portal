import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, FormArray } from '@angular/forms';

import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';


@Component({
  selector: 'app-account-specification',
  templateUrl: './account-specification.component.html',
  styleUrls: ['./account-specification.component.css']
})
export class AccountSpecificationRpComponent  implements OnInit{
  
  
  constructor(
    private router: Router,
    private api: ApiService,
    private storage: StorageService,
    private ngxService: NgxUiLoaderService,
    private route: ActivatedRoute  
    ) { }

  customerid: any = '';

  rependingData: any = [];

  ngOnInit(): void {

    this.route.paramMap.subscribe((params: ParamMap) => {
      this.customerid = params.get('id');

      if(Object.keys(this.storage.rependingData).length < 1 || this.storage.rependingData < 1) {
        this.getDataById(this.customerid);
        sessionStorage.removeItem('r-account_services')
      } else {
        this.rependingData = this.storage.rependingData;
        
        if(this.rependingData.stateoforigin != null ) {
          this.getLga();
        }
      }
    });

    this.getStoredAccountServices()

    this.getState();
    this.getincomeBracketCode();
    this.getSectorList();
    this.getOccupationList();
    this.getBusinessList();
    
    this.getAccountTypes();
    this.getBranchNames();
    this.getAgentNames();       
    
  };
  
  getDataById(customerid: any) {
    let data = {
      id: customerid
    };

    sessionStorage.setItem('customer', customerid);

    this.ngxService.start();

    this.api.getDetailsById(data).subscribe({
      next: (
        (res: any) => {
          // console.log(res);
          this.storage.timeOutNgxService();
          if (res.statuscode === "00") {
            this.storage.rependingData = res.data;
            this.rependingData = res.data;

            this.resolveRependingData(res);
          }
          else if(res.statuscode === "96") {
            this.storage.status_message_error(res);
          }
          else {
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

  resolveRependingData(res: any) {

    const storedValues = sessionStorage.getItem('r-account_services');
    if (!storedValues) {
      if(this.rependingData.acctService.length > 0) {
        let selectedValues = this.rependingData.acctService.split(',');            
        
        this.account_services.forEach(item => {
          item.checked = selectedValues.includes(item.name);
        });
  
      }
    }

    if(this.rependingData.stateoforigin != null) {
      this.getLga();
    }
    this.storage.rependingDocuments = res.data.document;

    if(res.data.dateofIncorparation !== null || res.data.dateofIncorparation != 'undefined') {
      let a1 = res.data.dateofIncorparation.slice(0, 10);
      let a2 = res.data.dateofIncorparation.slice(11, 21);
      this.rependingData.dateofIncorparation = a1 + 'T' + a2 + '00Z';
      
    };              

    if(this.rependingData.branch_code.length > 0) {
      this.getAccountOfficers();
      this.displayAccountOfficerList = true;
    } else {
      this.displayAccountOfficerList = false;
    };
  }

  getStoredAccountServices() {
    const storedValues = sessionStorage.getItem('r-account_services');
    if (storedValues) {
      const selectedValues = storedValues.split(',');
      this.account_services.forEach(item => {
        item.checked = selectedValues.includes(item.name);
      });
    };
  }

  stockExchange() {    
    if(this.rependingData.iscompanyquoted == 'no') {
      this.rependingData.companyquotedname = '';
    }
  };

  getAccountTypes() {
    
    this.ngxService.startBackground('account-types');

    this.api.getCorporateAccountTypes().subscribe({
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

  getSectorList() {
    
    this.ngxService.startBackground('selected-list');

    this.api.getSectorList().subscribe({
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

    this.api.getOccCode().subscribe({
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
    this.api.getIncomeBracket().subscribe({
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

    this.api.getBusinessType().subscribe({
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

    this.api.getBranchName().subscribe({
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

    this.api.getAgentNames().subscribe({
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
      branch_code: this.rependingData.branch_code
    };

    this.api.getAccountOfficerName(data).subscribe({
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

    this.api.getState(data).subscribe({
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
      state_code: this.rependingData.stateoforigin
    };

    // console.log(data);
    

    this.api.getLga(data).subscribe({
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
            // console.log(err);
            
            this.ngxService.stopBackground('get-lga');
            this.storage.global_error();
          }
        )
      });
  };

  saveSelectedAccountServices(): void {
    const selected: string[] = this.account_services
      .filter(x => x.checked)
      .map(x => x.name);

    const serialized: string = selected.join(',');
    
    sessionStorage.setItem('r-account_services', serialized)
  }


  next() {
    this.saveSelectedAccountServices();

    
    this.storage.rependingData = this.rependingData;

    this.router.navigate(["/r/account-signatory"]);
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

  // selectedValues!: string[];

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

  imageLogo: any = this.storage.imageLogo;
}
