import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';
import Swal from 'sweetalert2';

interface AccountHeldForm {
  nameANDaddress: string;
  accountName: string;
  accountNumber: string;
  status: string;
  // Add more properties as needed
}

interface ContactPersonForm {
  fullname: string;
  jobtitle: string;
  email: string;
  mobilenumber: string;
  officeaddress: string;
  // Add more properties as needed
}

interface SignatoriesForm {
  title: string;
  last_name: string;
  first_name: string;
  marital_status: string;
  email: string;
  alt_phonenumber: string;
  dob: string;
  gender: string;
  lga: string;
  permit_expiry_date: string;
  permit_issue_date: string;
  religion: string;
  resident_permit_number: string;
  residential_address: string;
  residential_address_lga: string;
  residential_address_state: string;
  state_of_origin: string;
  middle_name: string;
  mother_maidenname: string;
  phonenumber: string;
  place_of_birth: string;
  place_of_permit_issue: string;
  nationality: string;
  // state: string;
  nextofkinName: string;
  meansofidentification: string;
  identificationnumber: string;
  tin: string;
  idissuerdate: string;
  idexpirydate: string;
  occupation: string;
  jobtitle: string;
  office: string;
  citizenshipstatus: string;
  bvn: string;
  ssn: string;
  mailingaddress: string;
  countryofResidency: string;
  // Add more properties as needed
}


@Component({
  selector: 'app-account-signatory',
  templateUrl: './account-signatory.component.html',
  styleUrls: ['./account-signatory.component.css']
})
export class AccountSignatoryRpComponent implements OnInit {
  
  constructor(
    private router: Router,
    private apiService: ApiService,
    private storage: StorageService,
    private ngxService: NgxUiLoaderService
    ) {

  }

  rependingData: any = {};

  ngOnInit(): void {

    if(sessionStorage.getItem('customer') !== null) {
      this.routeTo = sessionStorage.getItem('customer');
    };
    
    if(Object.keys(this.storage.rependingData).length < 1) {
      this.router.navigate(['/r/account-specification/' + this.routeTo]);
    } else {
      
      this.rependingData = this.storage.rependingData;

      this.getTitle();
      this.getGender();
      this.getReligionList();
      this.getMaritalList();
      this.getNationality();
  
      this.getResidenceState();
  
      const storedAccountData = this.rependingData.accountheld;
      if (storedAccountData) {
        this.account_held_form = this.rependingData.accountheld;
      }
      const storedContactData = this.rependingData.contact;
      if (storedContactData) {
        this.contact_person_form = this.rependingData.contact;
      }
      const storedSignatoryData = this.rependingData.signatories;
      if (storedSignatoryData) {
        this.signatories_form = this.rependingData.signatories;

        // console.log(this.signatories_form);
        
        this.signatories_form.forEach((item) => { 
          
          if(item.dob.length > 0) {
            let a1 = item.dob.slice(0, 10);
            let a2 = item.dob.slice(11, 21);
            
            item.dob = a1 + 'T' + a2 + '00Z';            
          }; 
          
          if(item.permit_issue_date.length > 0) {
            let a1 = item.permit_issue_date.slice(0, 10);
            let a2 = item.permit_issue_date.slice(11, 21);
            
            item.permit_issue_date = a1 + 'T' + a2 + '00Z';            
          }; 
          
          if(item.permit_expiry_date.length > 0) {
            let a1 = item.permit_expiry_date.slice(0, 10);
            let a2 = item.permit_expiry_date.slice(11, 21);
            
            item.permit_expiry_date = a1 + 'T' + a2 + '00Z';            
          }; 
          
          if(item.idissuerdate.length > 0) {
            let a1 = item.idissuerdate.slice(0, 10);
            let a2 = item.idissuerdate.slice(11, 21);
            
            item.idissuerdate = a1 + 'T' + a2 + '00Z';            
          }; 
          
          if(item.idexpirydate.length > 0) {
            let a1 = item.idexpirydate.slice(0, 10);
            let a2 = item.idexpirydate.slice(11, 21);
            
            item.idexpirydate = a1 + 'T' + a2 + '00Z';            
          }; 
          
        });

      }
    }
    
  }
  
  getGender() {
    this.ngxService.startBackground('get-gender');

    this.apiService.getgender().subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-gender');
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
          this.ngxService.stopBackground('get-title');
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
          this.ngxService.stopBackground('get-title');
          this.storage.global_error();
        }
      )
    });
  };
  
  getNationality() {
    this.lgaList = [];
    this.stateNames = [];
    
    this.ngxService.startBackground('get-nationality');

    this.apiService.getNationality().subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-nationality');
          // console.log(resp);

          if(resp.statuscode == '00'){
            this.nationalities = resp.data;
            sessionStorage.setItem('nationalityList', JSON.stringify(resp.data));

            this.signatories_form.forEach((item) => {                   
              if(item.nationality.length > 0 && item.nationality.toLowerCase() == 'nigeria' ) {
                this.getState(item.nationality);
              }
            });

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

  getState(countryName: any) {

    console.log(countryName.toLowerCase());

    if(countryName.toLowerCase() == 'nigeria') {
      
      this.lgaList = [];
  
      let country = this.nationalities.filter((x: any) => x.countryName == countryName);
      
      let data: any = {
        country_code: country[0].countryCode
      };
  
      // console.log(data);    
      this.ngxService.startBackground('get-state');
  
      this.apiService.getState(data).subscribe({
        next: (
          (resp: any) => {
            this.ngxService.stopBackground('get-state');
            // console.log(resp);
  
            if(resp.statuscode == '00'){
              this.stateNames = resp.data;
              sessionStorage.setItem('stateList', JSON.stringify(resp.data));
  
              this.signatories_form.forEach((item) => {
                if(item.state_of_origin.length > 0) {
                  this.getLga(item.nationality,item.state_of_origin);
                }
              });
  
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
    else {
      this.signatories_form.forEach((item) => {
        if(item.nationality.length > 0 && item.nationality.toLowerCase() != 'nigeria' ) {
          this.stateNames = [];
          this.lgaList = [];
          item.state_of_origin = '';
          item.lga = '';
        }
      });
    }

  }

  getLga(countryName: any, stateName: any) {
    
    let country = this.nationalities.filter((x: any) => x.countryName == countryName);
    
    // console.log(this.stateNames);
    
    let state = this.stateNames.filter((x: any) => x.stateName == stateName);

    // console.log(state);
    
    let data: any = {
      country_code: country[0].countryCode,
      state_code: state[0].stateCode
    };

    // console.log(data);
    
    this.ngxService.startBackground('get-lga');

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

  getResidenceState() {
    this.lgaList = [];
    
    let data: any = {
      country_code: 'NGR'
    };

    // console.log(data);    
    
    this.ngxService.startBackground('get-residence-state');

    this.apiService.getState(data).subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-residence-state');
          // console.log(resp);
          if(resp.statuscode == '00'){
            this.stateResidenceNames = resp.data;
            sessionStorage.setItem('stateResidenceList', JSON.stringify(resp.data));
            
            this.signatories_form.forEach((item) => {
              if(item.residential_address_state.length > 0) {
                this.getResidenceLga(item.residential_address_state);
              };
            });

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
          this.ngxService.stopBackground('get-residence-state');
          this.storage.global_error();
        }
      )
    });
  };

  getResidenceLga(stateName: any) {
    this.ngxService.startBackground('get-residence-lga');
    
    let state = this.stateResidenceNames.filter((x: any) => x.stateName == stateName);

    // console.log(state);    
    
    let data: any = {
      country_code: 'NGR',
      state_code: state[0].stateCode
    };

    // console.log(data);

    this.apiService.getLga(data).subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-residence-lga');
          // console.log(resp);
          if(resp.statuscode == '00'){
            this.lgaResidenceList = resp.data;
            sessionStorage.setItem('lgaResidenceList', JSON.stringify(resp.data));
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
            this.ngxService.stopBackground('get-residence-lga');
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
          this.ngxService.stopBackground('get-religion');
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
          this.ngxService.stopBackground('get-religion');
          this.storage.global_error();
        }
      )
    });
  };
  
  getMaritalList() {
    this.ngxService.startBackground('get-marital-status');

    this.apiService.getMaritalStatus().subscribe({
      next: (
        (resp: any) => {
          this.ngxService.stopBackground('get-marital-status');
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
          this.ngxService.stopBackground('get-marital-status');
          this.storage.global_error();
        }
      )
    });
  };

  account_held_form: AccountHeldForm[] = [];
  contact_person_form: ContactPersonForm[] = [];
  signatories_form: SignatoriesForm[] = [];

  addAccountHeldForm() {
    this.account_held_form.push({ nameANDaddress: '', accountName: '', accountNumber: '', status: '' });
  };

  removeAccountHeldForm(index: number) {
    this.account_held_form.splice(index, 1);
  };  

  addContactPersonForm() {
    this.contact_person_form.push({ fullname: '', jobtitle: '', email: '', mobilenumber: '', officeaddress: ''});
  };

  removeContactPersonForm(index: number) {
    this.contact_person_form.splice(index, 1);
  };

  addSignatoriesForm() {
    this.signatories_form
    .push(
      { 
        email: '', gender: '', alt_phonenumber: '',
        dob: '', first_name: '', last_name: '',lga: '', 
        marital_status: '', religion: '', resident_permit_number: '', 
        permit_expiry_date: '', permit_issue_date: '',
        residential_address: '',residential_address_state:'',
         residential_address_lga: '',
        state_of_origin: '', title: '', middle_name: '', 
        mother_maidenname: '', phonenumber: '', place_of_birth: '', 
        place_of_permit_issue: '', nationality: '',
        // state: '', 
        nextofkinName: '', meansofidentification: '', 
        identificationnumber: '', tin: '', idissuerdate: '', 
        idexpirydate: '', occupation: '',
        jobtitle: '', 
        office: '', citizenshipstatus: '', bvn: '',
        ssn: '', mailingaddress: '', countryofResidency: ''
      }
    );
  };

  removeSignatoriesForm(index: number) {
    this.signatories_form.splice(index, 1);
  };

  
  dualCitizenship(index: number) {    
    if(this.signatories_form[index].citizenshipstatus == 'No') {
      this.signatories_form[index].ssn = '';
      this.signatories_form[index].countryofResidency = '';
    }    
  };

  lgaList: any = [];
  lgaResidenceList: any = [];
  titleList: any = [];
  genderList: any = [];
  stateNames: any = [];
  stateResidenceNames: any = [];
  maritalList: any = [];
  religionList: any = [];
  nationalities: any = [];
  
  progress_bar: Boolean = false

  selected_citizenship: any = [];

  dual_citizenship = [
    {
      id: 1,
      name: 'Yes'
    },
    {
      id: 2,
      name: 'No'
    }
  ];


  next() {

    if(this.account_held_form.length < 1) {
      Swal.fire({
        html: `Please Add At Least One Account Detail`,
        icon: 'warning',
        customClass: 'swal-wide',
        confirmButtonColor: '#005D30',
        confirmButtonText: 'OK'
      });  
    } else if(this.contact_person_form.length < 1) {
      Swal.fire({
        html: `Please Add At Least One Contact Person`,
        icon: 'warning',
        customClass: 'swal-wide',
        confirmButtonColor: '#005D30',
        confirmButtonText: 'OK'
      });  
    } else if(this.signatories_form.length < 1) {
      Swal.fire({
        html: `Please Add At Least One Signatory`,
        icon: 'warning',
        customClass: 'swal-wide',
        confirmButtonColor: '#005D30',
        confirmButtonText: 'OK'
      });  
    } else {

      this.rependingData.accountheld = this.account_held_form;
      
      this.rependingData.contact = this.contact_person_form;

      this.rependingData.signatories = this.signatories_form;

      this.router.navigate(["/r/upload-document"]);
      
    }
  };

  
  routeTo: any = '';
  imageLogo: any = this.storage.imageLogo;
  
  back() {
    this.router.navigate(["/r/account-specification/" + this.routeTo]);
  }
  
  // validateInput(input: string): boolean {
  //   // Perform your validation logic here
  //   // Return true if input is valid, false otherwise
  //   return input.trim().length > 0;
  // }

}
