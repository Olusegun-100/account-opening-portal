import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-review-information',
  templateUrl: './review-information.component.html',
  styleUrls: ['./review-information.component.css']
})
export class ReviewInformationComponent {

  constructor(
    private domSanitizer: DomSanitizer,
    private api: ApiService,
    private router: Router,
    private storage: StorageService
  ) { }

  routeTo: any = '';
  
  ngOnInit(): void {
    if(sessionStorage.getItem('customer') !== null) {
      this.routeTo = sessionStorage.getItem('customer');
      this.customerId = this.routeTo;
    };
    
    if(Object.keys(this.storage.rependingData).length < 1) {
      this.router.navigate(['/r/personal-information/' + this.routeTo]);
    } else {
      this.rependingData = this.storage.rependingData;
      this.rependingDocs = this.storage.rependingDocuments;
      this.selectedAccountType();
      
      // console.log(this.rependingData);
      
      let branch = this.storage.getBranchName(this.rependingData.branch_code);//JSON.parse(sessionStorage.getItem('branchList')!);
      this.branch = branch[0];
      
      let accttype = this.storage.getAccountTypeCode(this.rependingData.accttypes);
      // console.log(accttype );

      this.accountTypeName = accttype[0].acct_type;
      this.accountTypeProdCode = accttype[0].prodCode;
      
      // console.log(this.branch);
      // console.log(this.acct_type_id);

      this.getNames();
    }
  }

  accountTypeName: any = '';
  accountTypeProdCode: any = '';

  getNames() {    
      let customerinfo = this.rependingData;
      // this.customerInfo = cus_info;
      // this.title = cus_info.selected_title;
      if(customerinfo.gender !== null) {
        this.gender = (this.storage.getGenderInfo(customerinfo.gender))[0].description;
      };
      if(customerinfo.maritalstatus !== null) {
        this.marital = (this.storage.getMaritalInfo(customerinfo.maritalstatus))[0].description;
      };
      if(customerinfo.religion !== null) {
        this.religion = (this.storage.getReligionInfo(customerinfo.religion))[0].description;
      };
      if(customerinfo.nationality !== null) {
        this.nationality = (this.storage.getNationalityInfo(customerinfo.nationality))[0].countryName;
      };
      if(customerinfo.stateoforigin !== null) {
        this.state = (this.storage.getStateInfo(customerinfo.stateoforigin))[0].stateName;
      };
      if(customerinfo.lga !== null) {
        this.lga = (this.storage.getLgaInfo(customerinfo.lga))[0].lgaName;
      };    
    
      if(customerinfo.sectcode !== null) {
        this.sector = (this.storage.getSectorInfo(customerinfo.sectcode))[0].description;
      };
      if(customerinfo.occCode !== null) {
        this.occupation = (this.storage.getOccInfo(customerinfo.occCode))[0].description;
      };
      if(customerinfo.businesscode !== null) {
        this.business = (this.storage.getBusinessTypeInfo(customerinfo.businesscode))[0].decription;
      };
      if(customerinfo.incomeBrackcode !== null) {
        this.income = (this.storage.getIncomeBracketInfo(customerinfo.incomeBrackcode))[0].incomeBracketdescription;
      };
      if(customerinfo.empStatcode !== null) {
        this.employmentStatus = (this.storage.getEmploymentInfo(customerinfo.empStatcode))[0].description;
      };
  };
  
  selectedAccountType() {   
    let x = this.storage.getAccountTypeInfo(this.rependingData.accttypes);
    if(x.length < 1)
     return;
    else if (((x[0]).acct_type.toLowerCase()).includes("corporate")) {
      // console.log('true');      
      return  this.displayCorporateCard = true;
    } else {
      // console.log('false');
      return this.displayCorporateCard = false;
    }
  };

  // download(info: any) {
  //   this.base64image = this.domSanitizer.bypassSecurityTrustUrl(
  //     info.file
  //     );
  //     this.base64imageName = info.file_name;
  // };


  submit(event: any) {
    event.stopPropagation();
    const content = {
      "id": this.customerId,
      "email": this.rependingData.email,
      "lga": this.rependingData.lga,
      "phonenumber": this.rependingData.phonenumber, 
      "state": this.rependingData.stateoforigin,
      "alt_phonenumber": (this.rependingData.alt_phonenumber == 'undefined' || this.rependingData.alt_phonenumber == null)? '' : this.rependingData.alt_phonenumber,
      "bvn":this.rependingData.bvn,
      "contact_addressone":  this.rependingData.phonenumber,
      "contact_addresstwo": (this.rependingData.alt_phonenumber == 'undefined' || this.rependingData.alt_phonenumber == null)? '' : this.rependingData.alt_phonenumber,
      "dob": this.rependingData.dob,
      "first_name":this.rependingData.firstname,
      "gender": this.rependingData.gender,
      "last_name":this.rependingData.lastname,
      "marital_status":this.rependingData.maritalstatus,
      "middle_name":this.rependingData.middlename,
      "mother_maidenname": this.rependingData.mothersmaidenname,
      "nationality":this.rependingData.nationality,
      "nxk_lastname": this.rependingData.nextofkin_surname,
      "nxk_firstname":this.rependingData.nextofkin_firstname,
      "nxk_phonenumber":this.rependingData.nextofkin_phonenumber,
      "nxk_relationship":this.rependingData.nextofkin_relationship,
      "nxk_email":this.rependingData.nextofkin_email,
      "nxk_address":this.rependingData.nextofkin_residentialadd,

      "permit_expiry_date": this.rependingData.permitExpiryDate.slice(0, 10), //2023-05-01,
      "permit_issue_date": this.rependingData.permitIssueDate.slice(0, 10),
      "place_of_birth": this.rependingData.placeofbirth,
      "place_of_permit_issue":this.rependingData.placeOfpermitissue,

      "religion":this.rependingData.religion,
      "resident_permit_number":this.rependingData.residentpermitnumber,
      "residential_address":this.rependingData.residentialaddress,
      "residential_address_lga":this.rependingData.addlga,
      "residential_address_state":this.rependingData.stateofresidence,
      "state_of_origin":"",
      "title":this.rependingData.title,
      "address_description": "",
      "country": "",
      "postal_code": "",
      "acct_name": this.rependingData.firstname + ' ' + this.rependingData.lastname,
      "rcno": this.rependingData.rcno,  
      "tin":this.rependingData.tin,
      "status": this.rependingData.status == "initiator-repending" ? "" : this.rependingData.status == "repending" ? "pending" : "",
      "supervisorId": this.rependingData.status == "initiator-repending" ? "" : this.rependingData.status == "repending" ? this.rependingData.supervisorId : "",
      "staffId": this.rependingData.status == "initiator-repending" ? "" : this.rependingData.status == "repending" ? this.rependingData.staffId : "",
      
      "branch_code": this.branch.branch_code,
      "account_officer": this.rependingData.officerName,
      "acct_type": this.accountTypeProdCode,

      
      // account_officer: this.accountOfficer,
      // title:this.customerInfo.selected_title,
      empStat: this.rependingData.empStatcode,
      incBrack:this.rependingData.incomeBrackcode,
      busType: this.rependingData.businesscode,
      occCode:this.rependingData.occCode,
      sectorCode: this.rependingData.sectcode,

      employstatname:this.employmentStatus,
      incCBracketname:this.income,
      busTypename:this.business,
      occCodename: this.occupation,
      sectCodename: this.sector,
      genderName:this.gender,
      maritalstatusName:this.marital,
      religionName:this.religion,
      stateName:this.state,
      lgaName:this.lga,
      nationalityName:this.nationality,
      // "staffId":this.rependingData.staffId,
      documents: this.rependingDocs.map((i: any) => ({
        "file_name": i.file_name,
        "doc_type": i.doc_type,
        "file": i.file
    }))

    }
    this.isLoading = true;

    // console.log(content);
    // setTimeout(()=> this.isLoading = false, 5000);
    this.api.re_submit(content).subscribe({
      next: (
        (res: any) => {
          // console.log(res);
          // this.displayModal = "none";
          if (res.statuscode === "00") {
            Swal.fire({
              html: `${res.statusmessage.toUpperCase()}`,
              // html: `ACCOUNT SUCCESSFULLY CREATED`,
              icon: 'success',
              customClass: 'swal-wide',
              confirmButtonColor: '#005D30',
              confirmButtonText: 'OK'
            }).then(() => {
              this.isLoading = false;
              sessionStorage.clear();
              this.storage.documents = [];
              this.storage.accountList = null;
              this.router.navigate(["/s/success"]);
            })
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
          this.isLoading = false;
          this.storage.global_error();
        }
      )
    });    
  };

  
  branch: any = [];
  
  gender: any = null;
  marital: any = null;
  nationality: any = null;
  state: any = null;
  lga: any = null;
  religion: any = null;
  employmentStatus: any = null;
  sector: any = null;
  income: any = null;
  occupation: any = null;
  business: any = null;
  title: any = null;


  rependingData: any = [];
  rependingDocs: any = [];
  customerId: any = '';
  // branch_code : any = '';
  acct_type_id : any = '';

  imageSpinner = this.storage.imageSpinner;

  terms: Boolean = false;
  indemnity: Boolean = false;
  isLoading: Boolean = false;
  displayCorporateCard: Boolean = false;
  personaldetails: any = {};
  addressdetails: any = {};
  meansofidentification: any = {};
  nextofkin: any = {};
  userpersonaldetails: any = {};
  employmentdetails: any = {};
  documents: any = [];
  base64imageName: any = '';
  base64image: any = '';
  accountType: any = '';
}
