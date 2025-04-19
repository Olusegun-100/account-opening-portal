import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor(
    private ngxservice: NgxUiLoaderService) { }

  create_UUID() {
    var dt = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      var r = (dt + Math.random()*16)%16 | 0;
      dt = Math.floor(dt/16);
      return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    return uuid;
  }

  MustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
        const control = formGroup.controls[controlName];
        const matchingControl = formGroup.controls[matchingControlName];
        if (matchingControl.errors && !matchingControl.errors['mustMatch']) {
        return;
        }
        if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ mustMatch: true });
        } else {
        matchingControl.setErrors(null);
        }
        }
    };

    global_error(){
      Swal.fire({
        html: 'Oppps! Something went wrong!',
        icon: 'warning',
        width: '500px',
        confirmButtonColor: '#005D30',
        confirmButtonText: 'OK'
      });
    }

    status_message_error(info: any){
      Swal.fire({
        html: `${info.statusmessage.toUpperCase()}`,
        icon: 'warning',
        width: '500px',
        confirmButtonColor: '#005D30',
        confirmButtonText: 'OK'
      });
    }

    accountList: any = [];
    branchList: any = [];
    officerList: any = [];
    incomeBracketList: any = [];
    nationalityList: any = [];
    employmentList: any = [];
    sectorList: any = [];
    occList: any = [];
    businessList: any = [];
    religionList: any = [];
    maritalList: any = [];
    genderList: any = [];
    titleList: any = [];

    getAccountTypeInfo(id: any) {
      if(JSON.parse(sessionStorage.getItem('accountList')!) !== null) { // for initial requests
        let accountList = JSON.parse(sessionStorage.getItem('accountList')!);
        return accountList.filter((x: any) => x.acct_type == id);
      }
    };
    getAccountTypeProdCode(id: any) {
      if(JSON.parse(sessionStorage.getItem('accountList')!) !== null) { // for initial requests
        let accountList = JSON.parse(sessionStorage.getItem('accountList')!);
        return accountList.filter((x: any) => x.acct_type == id);
      }
    };

    getNationalityInfo(countryCode: any) {
      if(JSON.parse(sessionStorage.getItem('nationalityList')!) !== null) { // for initial requests
        let nationalityList = JSON.parse(sessionStorage.getItem('nationalityList')!);
        return nationalityList.filter((x: any) => x.countryCode == countryCode);
      }
    }; 

    getStateInfo(stateCode: any) {
      if(JSON.parse(sessionStorage.getItem('stateList')!) !== null) { // for initial requests
        let stateList = JSON.parse(sessionStorage.getItem('stateList')!);
        return stateList.filter((x: any) => x.stateCode == stateCode);
      }
    }; 

    getLgaInfo(lgaCode: any) {
      if(JSON.parse(sessionStorage.getItem('lgaList')!) !== null) { // for initial requests
        let lgaList = JSON.parse(sessionStorage.getItem('lgaList')!);
        return lgaList.filter((x: any) => x.lgaCode == lgaCode);
      }
    }; 

    getEmploymentInfo(code: any) {
      if(JSON.parse(sessionStorage.getItem('employment')!) !== null) { // for initial requests
        let employmentList = JSON.parse(sessionStorage.getItem('employment')!);
        let t = employmentList.filter((x: any) => x.code == code);
        //  console.log(t);         
        return t
      }      
    }; 

    getIncomeBracketInfo(incomeBracketCode: any) {
      if(JSON.parse(sessionStorage.getItem('incomeBracketList')!) !== null) { // for initial requests
        let incomeBracketList = JSON.parse(sessionStorage.getItem('incomeBracketList')!);
        let t = incomeBracketList.filter((x: any) => x.incomeBracketCode == incomeBracketCode);
        //  console.log(t);         
        return t
      }       
    };

    getSectorInfo(code: any) {
      if(JSON.parse(sessionStorage.getItem('sectorList')!) !== null) { // for initial requests
        let sectorList = JSON.parse(sessionStorage.getItem('sectorList')!);
        let t = sectorList.filter((x: any) => x.code == code);
        //  console.log(t);         
        return t;
      }
      
    };  

    getOccInfo(code: any) {
      if(JSON.parse(sessionStorage.getItem('occList')!) !== null) { // for initial requests
        let occList = JSON.parse(sessionStorage.getItem('occList')!);
        let t = occList.filter((x: any) => x.code == code);
        //  console.log(t);         
        return t;
      }
      
    };

    getBusinessTypeInfo(code: any) {
      if(JSON.parse(sessionStorage.getItem('businessList')!) !== null) { // for initial requests
        let businessList = JSON.parse(sessionStorage.getItem('businessList')!);
        let t = businessList.filter((x: any) => x.code == code);
        // console.log(t);
        return t
      }       
    }; 

    getReligionInfo(code: any) {
      if(JSON.parse(sessionStorage.getItem('religionList')!) !== null) { // for initial requests
        let religionList = JSON.parse(sessionStorage.getItem('religionList')!);
        return religionList.filter((x: any) => x.code == code);
      }       
    };

    getMaritalInfo(code: any) {
      if(JSON.parse(sessionStorage.getItem('maritalList')!) !== null) { // for initial requests
        let maritalList = JSON.parse(sessionStorage.getItem('maritalList')!);
        return maritalList.filter((x: any) => x.code == code);
      }       
    }; 

    getGenderInfo(code: any) {
      if(JSON.parse(sessionStorage.getItem('genderList')!) !== null) { // for initial requests
        let genderList = JSON.parse(sessionStorage.getItem('genderList')!);
        return genderList.filter((x: any) => x.code == code);
      }       
    };

    getTitleInfo(code: any) {
      if(JSON.parse(sessionStorage.getItem('titleList')!) !== null) { // for initial requests
        let titleList = JSON.parse(sessionStorage.getItem('titleList')!);
        let t = titleList.filter((x: any) => x.code == code);
        // console.log(t);         
        return t;
      }       
    };

    getBranchNameInfo(branch_code: any) {
      if(JSON.parse(sessionStorage.getItem('branchList')!) !== null) {
        let branchList = JSON.parse(sessionStorage.getItem('branchList')!);
        // console.log(branchList);
        return branchList.filter((x: any) => x.branch_code == branch_code);
      }
    };

    getBranchCode(branch_name: any) {
      if(JSON.parse(sessionStorage.getItem('branchList')!) !== null) {
        let branchList = JSON.parse(sessionStorage.getItem('branchList')!);
        // console.log(branchList);
        return branchList.filter((x: any) => x.branch_name == branch_name);
      }
    };
    
    getBranchName(branch_code: any) {
      if(JSON.parse(sessionStorage.getItem('branchList')!) !== null) {
        let branchList = JSON.parse(sessionStorage.getItem('branchList')!);
        // console.log(branchList);
        return branchList.filter((x: any) => x.branch_code == branch_code);
      }
    };

    getAccountTypeCode(acct_type: any) {
      // console.log(acct_type);
      
      if(JSON.parse(sessionStorage.getItem('accountList')!) !== null) {
        let accountList = JSON.parse(sessionStorage.getItem('accountList')!);
        // console.log(accountList);
        return accountList.filter((x: any) => x.acct_type == acct_type);
      }
    };

    imageSpinner: any = 'assets/images/spin.gif';
    imageLogo: any = 'assets/images/itmb-logo.png';

    imageV1 = "assets/images/v1.svg";
    imageV2 = "assets/images/v2.svg";
    imageV3= "assets/images/v3.svg";
    imageV4 = "assets/images/v4.svg";
    imageV5 = "assets/images/v5.svg";

    documents: any[] = [];

    months: any[] = [
      {
        name: 'jan'
      },
      {
        name: 'feb'
      },
      {
        name: 'mar'
      },
      {
        name: 'apr'
      },
      {
        name: 'may'
      },
      {
        name: 'jun'
      },
      {
        name: 'jul'
      },
      {
        name: 'aug'
      },
      {
        name: 'sep'
      },
      {
        name: 'oct'
      },
      {
        name: 'nov'
      },
      {
        name: 'dec'
      },
    ];

    renameMonth (month: any) {
      let newMonth = ''
      if(month == "jan") {
        newMonth = '01'
      }
      if(month == "feb") {
        newMonth = '02'
      }
      if(month == "mar") {
        newMonth = '03'
      }
      if(month == "apr") {
        newMonth = '04'
      }
      if(month == "may") {
        newMonth = '05'
      }
      if(month == "jun") {
        newMonth = '06'
      }
      if(month == "jul") {
        newMonth = '07'
      }
      if(month == "aug") {
        newMonth = '08'
      }
      if(month == "sep") {
        newMonth = '09'
      }
      if(month == "oct") {
        newMonth = '10'
      }
      if(month == "nov") {
        newMonth = '11'
      }
      if(month == "dec") {
        newMonth = '12'
      }
      return newMonth;
    }

    timeOutNgxService() {
      setTimeout(() => {
        this.ngxservice.stop();
      }, 1);
    }
    rependingCustomerId: string = '';
    rependingData: any = {};
    rependingDocuments: any = [];

    public accountMandate: any = "assets/files/Account-Opening-Mandate.pdf"
    public principalOfficer: any = "assets/files/Principal-Officer.pdf"
    public referenceForm: any = "assets/files/Reference-Form.pdf"
}