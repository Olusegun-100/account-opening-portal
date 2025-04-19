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

  ngOnInit(): void {
    console.log(this.storage.documents);
    
    if(this.storage.documents.length < 1) {
      this.router.navigate(["/upload-document"]);
    } else {
      if(JSON.parse(sessionStorage.getItem('selectedAccountOfficer')!) !== null) {
        this.accountOfficer = JSON.parse(sessionStorage.getItem('selectedAccountOfficer')!);
      }
      if(JSON.parse(sessionStorage.getItem('selectedAccountType')!) !== null) {
        let accountTypeid = JSON.parse(sessionStorage.getItem('selectedAccountType')!);
        console.log(accountTypeid);
        
        if(accountTypeid !== null) {
          let accountInfo = this.storage.getAccountTypeInfo(accountTypeid);
          this.prodCode = (this.storage.getAccountTypeProdCode(accountTypeid))[0].prodCode;
          if(accountInfo.length > 0) {
            this.accountTypeName = accountInfo[0];
          }
          if (((accountInfo[0])
            .acct_type
            .toLowerCase()).includes("corporate")) {
            this.displayCorporateCard = true
          } else {
            this.displayCorporateCard = false;
          }
        }
      };

      if(JSON.parse(sessionStorage.getItem('selectedBranch')!) !== null) {
        let branchcode = JSON.parse(sessionStorage.getItem('selectedBranch')!);
        if(branchcode !== null ) {
          let getBranch = this.storage.getBranchNameInfo(branchcode);
          this.branch = getBranch[0];
        };
      };
      
      if(JSON.parse(sessionStorage.getItem('customer_info')!) !== null) {
        let cus_info = JSON.parse(sessionStorage.getItem('customer_info')!);
        this.customerInfo = cus_info;
        this.title = cus_info.selected_title;
        if(cus_info.selected_gender !== null) {
          this.gender = (this.storage.getGenderInfo(cus_info.selected_gender))[0].description;
        };
        if(cus_info.selected_maritalStatus !== null) {
          this.marital = (this.storage.getMaritalInfo(cus_info.selected_maritalStatus))[0].description;
        };
        if(cus_info.selected_religion !== null) {
          this.religion = (this.storage.getReligionInfo(cus_info.selected_religion))[0].description;
        };
        if(cus_info.selected_nationality !== null) {
          this.nationality = (this.storage.getNationalityInfo(cus_info.selected_nationality))[0].countryName;
        };
        if(cus_info.selected_state !== null) {
          this.state = (this.storage.getStateInfo(cus_info.selected_state))[0].stateName;
        };
        if(cus_info.selected_lga !== null) {
          this.lga = (this.storage.getLgaInfo(cus_info.selected_lga))[0].lgaName;
        };
      };
      
      if(JSON.parse(sessionStorage.getItem('customer_info')!) !== null) {
        let cus_info2 = JSON.parse(sessionStorage.getItem('customer_info')!);
        this.customerInfo2 = cus_info2;
        if(cus_info2.selected_sector !== null) {
          this.sector = (this.storage.getSectorInfo(cus_info2.selected_sector))[0].description;
        };
        // if(cus_info2.selected_occupation !== null) {
        //   this.occupation = (this.storage.getOccInfo(cus_info2.selected_occupation))[0].description;
        // };
        if(cus_info2.selected_businessType !== null) {
          this.business = (this.storage.getBusinessTypeInfo(cus_info2.selected_businessType))[0].decription;
        };
        if(cus_info2.selected_income !== null) {
          this.income = (this.storage.getIncomeBracketInfo(cus_info2.selected_income))[0].incomeBracketdescription;
        };
        // if(cus_info2.selected_employment !== null) {
        //   this.employmentStatus = (this.storage.getEmploymentInfo(cus_info2.selected_employment))[0].description;
        // };
      };
  
      this.personaldetails = JSON.parse(sessionStorage.getItem('bvn_info')!);
      
      // this.nextofkin = JSON.parse(sessionStorage.getItem('getBvnDetails')!);
      this.userpersonaldetails = JSON.parse(sessionStorage.getItem('userpersonaldetails')!);
      
      this.nextofkin = JSON.parse(sessionStorage.getItem('nextofkin')!);
      
      // this.documents = JSON.parse(sessionStorage.getItem('documents')!);    
      this.documents = this.storage.documents;
      
      this.userpersonaldetails2 = JSON.parse(sessionStorage.getItem('userpersonaldetails2')!);
      
    };    
  }

  download(info: any) {
    this.base64image = this.domSanitizer.bypassSecurityTrustUrl(
      info.imageInfo.file
      );
    this.base64imageName = info.imageInfo.name;
  };
  
  view(info: any) {
    this.display = 'block';
    this.pdfSrc = info.imageInfo.file;
  }
  
  close(){
    this.display = 'none';
  };

  submit(event: any) {
    event.stopPropagation();
    Swal.fire({
      html: 'Are you sure?',
      icon: 'warning',
      customClass: 'swal-wide',
      allowOutsideClick: false,
      allowEscapeKey: false,
      allowEnterKey: false,
      showCancelButton: true,
      cancelButtonColor: '#d33',
      confirmButtonColor: '#b1a154',
      // confirmButtonColor: '#005D30',
      confirmButtonText: 'OK'
    }).then((result) => {
      if (result.isConfirmed) {
        const vals = this.personaldetails.dateofbirth.split('-');
        const day = vals[0];
        const month = vals[1];
        const year = vals[2];
        let newMonth = this.storage.renameMonth(month);
    
        const content = {
          // email: 'tunemessy@gmail.com',
          email: this.personaldetails.email,
          lga: this.customerInfo.selected_lga,
          phonenumber: this.personaldetails.phonenumber1, 
          state:this.customerInfo.selected_state,
          // nxk_lastname: 'Ade',
          nxk_lastname: this.nextofkin.surname,
          alt_phonenumber:this.personaldetails.phonenumber2,
          // bvn:'28933384095',
          bvn:this.personaldetails.bvn,
          contact_addressone:this.personaldetails.phonenumber1,
          contact_addresstwo:this.personaldetails.phonenumber2,
          dob: year + '-' + newMonth + '-' + day,
          gender: this.customerInfo.selected_gender,
          first_name:this.personaldetails.firstname,
          last_name:this.personaldetails.lastname,
          middle_name:this.personaldetails.middlename,
          // first_name:'Koey',
          // last_name:'shey',
          // middle_name:'sean',
          marital_status:this.customerInfo.selected_maritalStatus,
          mother_maidenname: this.userpersonaldetails.mothersmaidenname,
          nationality:this.customerInfo.selected_nationality,
          nxk_firstname:this.nextofkin.firstname,
          nxk_phonenumber:this.nextofkin.phonenumber.toString(),
          nxk_relationship:this.nextofkin.relationship,
          nxk_email:this.nextofkin.email,
          nxk_address:this.nextofkin.residentialadd,
          permit_expiry_date: this.userpersonaldetails.permitexpirydate.slice(0, 10), //2023-05-01
          permit_issue_date: this.userpersonaldetails.permitissuedate.slice(0, 10),
          place_of_birth: this.userpersonaldetails.placeofbirth,
          place_of_permit_issue:this.userpersonaldetails.placeofpermitissue,
          religion:this.customerInfo.selected_religion,
          resident_permit_number:this.userpersonaldetails.residentpermitnumber,
          residential_address:this.personaldetails.residentialaddress,
          residential_address_lga:this.personaldetails.lgaofresidence,
          residential_address_state:this.personaldetails.stateofresidence,
          state_of_origin: '',
          acct_type: this.prodCode,
          address_description: '',
          country: '',
          postal_code: '',
          date:'2022-12-03', 
          date_open: '2022-12-03',
          // "acct_name": 'eaz boy',
          acct_name: this.personaldetails.firstname + this.personaldetails.lastname,
          dateopen: '2022-12-03', 
          rcno: this.userpersonaldetails2.rcNumber,
          timestamp: '2022-12-03', 
          tin:this.userpersonaldetails2.tin,
          branch_code:this.branch.branch_code,
          account_officer: this.accountOfficer,
          title:this.customerInfo.selected_title,
          employstat: this.customerInfo2.selected_employment,
          incCBracket:this.customerInfo2.selected_income,
          busType: this.customerInfo2.selected_businessType,
          occCode:this.customerInfo2.selected_occupation,
          sectCode: this.customerInfo2.selected_sector,
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
          documents: this.documents.map((i: any) => ({
            file_name: i.imageInfo.name,
            doc_type: i.docName,
            file: i.imageInfo.file
          }))
        };
        console.log(content);
    
        this.isLoading = true;
        // setTimeout(()=> this.isLoading = false, 5000);
        this.api.submit(content).subscribe({
          next: (
            (res: any) => {
              console.log(res);
              if (res.statuscode === "00") {
                // Swal.fire({
                //   html: `${res.statusmessage.toUpperCase()}`,
                //   // html: `ACCOUNT SUCCESSFULLY CREATED`,
                //   icon: 'success',
                //   customClass: 'swal-wide',
                //   confirmButtonColor: '#005D30',
                //   confirmButtonText: 'OK'
                // }).then(() => {
                //   this.isLoading = false;
                //   sessionStorage.clear();
                // })
                this.storage.accountList = null;
                this.storage.documents = [];
                this.router.navigate(["/s/success"]);
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
      }
    });
  };

  pdfSrc: any = '';
  display: any = 'none';
  // accountType: any = null;
  accountTypeName: any = '';
  branch: any = {};
  accountOfficer: any = null;
  prodCode: any = null;
  customerInfo: any = {};
  customerInfo2: any = {};

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

  terms: Boolean = false;
  indemnity: Boolean = false;
  isLoading: Boolean = false;
  displayCorporateCard: Boolean = false;
  personaldetails: any = {};
  addressdetails: any = {};
  meansofidentification: any = {};
  nextofkin: any = {};
  userpersonaldetails: any = {};
  userpersonaldetails2: any = {};
  employmentdetails: any = {};
  documents: any = [];

  base64image: any = '';
  base64imageName: any = '';

  imageSpinner: any = this.storage.imageSpinner;
  
}