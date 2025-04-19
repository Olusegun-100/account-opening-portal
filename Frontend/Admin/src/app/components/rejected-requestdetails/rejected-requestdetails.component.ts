import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';
import Swal from 'sweetalert2';
import { NgxUiLoaderService } from 'ngx-ui-loader';


@Component({
  selector: 'app-rejected-requestdetails',
  templateUrl: './rejected-requestdetails.component.html',
  styleUrls: ['./rejected-requestdetails.component.css']
})
export class RejectedRequestdetailsComponent {
  
  constructor(
    private domSanitizer: DomSanitizer,
    private apiService: ApiService,
    private storage: StorageService,
    private ngxService: NgxUiLoaderService
  ) {    
  }
  
  ngOnInit(): void {
    if(JSON.parse(sessionStorage.getItem('staffId')!) !== null) {
      this.staffId = JSON.parse(sessionStorage.getItem('staffId')!);
    };
    
    if(JSON.parse(sessionStorage.getItem('supervisorId')!) !== null) {
      this.supervisorId = JSON.parse(sessionStorage.getItem('supervisorId')!);
    };
    
    this.getMoreDetails();
  }

  getMoreDetails() {
    if(JSON.parse(sessionStorage.getItem('customerid')!) !== null) {
      this.customerId = JSON.parse(sessionStorage.getItem('customerid')!);

      this.isLoading = true;
      let data = {
        id: this.customerId
      };
      
      this.ngxService.start();
        
      this.apiService.getFullDetails(data).subscribe({
        next: (
          (resp: any) => {
            // console.log(resp);
            // this.isLoading = false;
            this.storage.timeOutNgxService();
            if(resp.statuscode == '00'){
              this.accountdetails = resp.data;
              // console.log(this.accountdetails);

              if(this.accountdetails.firstname.length > 0) {
                this.customername = (this.accountdetails.firstname + ' ' + this.accountdetails.middlename + ' ' + this.accountdetails.lastname);
              }
              if(this.accountdetails.companyname.length > 0) {
                this.customername = this.accountdetails.companyname;
                // console.log(this.customername);              
              }
              if ((this.accountdetails.accttypes
                .toLowerCase()).includes("corparate")) {
                // .toLowerCase()).includes("corporate")) {
                this.displayCorporateCard = true
              } else {
                this.displayCorporateCard = false
              }
              
            }
            else if(resp.statuscode == '96'){
              Swal.fire({
                html: `${resp.statusmessage.toUpperCase()}`,
                icon: 'warning',
                width: '500px',
                confirmButtonColor: '#005D30',
                confirmButtonText: 'OK'
              });
            }
            else{
              this.storage.global_error();
            }
          }),
          error: (
            err => {
              // console.log(err);
              
              // this.isLoading = false;
            // this.storage.timeOutNgxService();
              this.storage.global_error();
          })
      })

    }
  }
  
  download(info: any) {
    // console.log(info);
    this.base64image = this.domSanitizer.bypassSecurityTrustUrl(
      info.file
      );
      this.base64imageName = info.file_name;
  };
  
  view(info: any) { 
    this.displayViewModal = 'block';
    this.pdfSrc = info.file;   
  }

  closePdfModal() {
    this.displayViewModal = 'none'
  }
  
  displayViewModal = 'none';
  pdfSrc = '';
  customername: any = '';

  base64image: any = '';
  base64imageName: any = '';
  staffId:any = '';
  supervisorId:any = '';
  accountdetails: any = {};
  isLoading: Boolean = false;
  displayCorporateCard: Boolean = false;
  customerId: any = '';

}
