import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { StorageService } from 'src/app/services/storage.service';
import Swal from 'sweetalert2';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { EncryptionService } from 'src/app/services/encryption.service';

@Component({
  selector: 'app-pending-requestdetails',
  templateUrl: './pending-requestdetails.component.html',
  styleUrls: ['./pending-requestdetails.component.css']
})
export class PendingRequestdetailsComponent {


  constructor(
    private domSanitizer: DomSanitizer,
    private apiService: ApiService,
    private storage: StorageService,
    private router: Router,
    private _e: EncryptionService,
    private ngxService: NgxUiLoaderService
  ) {    
  }


  ngOnInit(): void {
    if(JSON.parse(sessionStorage.getItem('staffId')!) !== null) {
      this.staffId = JSON.parse(sessionStorage.getItem('staffId')!);
      // console.log(this.staffId);
    }
    
    if(JSON.parse(sessionStorage.getItem('supervisorId')!) !== null) {
      this.supervisorId = JSON.parse(sessionStorage.getItem('supervisorId')!);
      // console.log(this.supervisorId);
    }
    
    this.getMoreDetails();
  };

  getMoreDetails() {
    if(JSON.parse(sessionStorage.getItem('customerid')!) !== null) {
      this.customerId = JSON.parse(sessionStorage.getItem('customerid')!);

      this.isLoading = true;
      let data = {
        id: this.customerId
      };
      
      // console.log(data);

      let req = this._e.eD(JSON.stringify(data));

    const request = {
      data: req
    };
      
    this.ngxService.start();
      
    this.apiService.getFullDetails(data).subscribe({
      next: (
        (resp: any) => {
          // console.log(resp);
          // this.isLoading = false;

        // let drc = this._e.dD(resp.data);
        // console.log(drc);
        
        //console.log(JSON.parse(JSON.parse(drc)));
        //console.log(JSON.stringify(drc));
        //console.log(JSON.parse(drc));
        
        
        // let resp = JSON.parse(drc);
          this.storage.timeOutNgxService();
          if(resp.statuscode == '00'){
            this.accountdetails = resp.data;

            // console.log(this.accountdetails);

            this.accountServices = this.accountdetails.acctService //.split(',');
            
            if ((this.accountdetails.accttypes
              .toLowerCase()).includes("corparate")) {
              // .toLowerCase()).includes("corporate")) {
                // Corparate
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
            this.storage.timeOutNgxService();
            
            // this.isLoading = false;
          // this.storage.timeOutNgxService();
            this.storage.global_error();
        })
    })

    }
  }
  
  view(info: any) {  
    this.displayViewModal = 'block';
    this.pdfSrc = info.file;   
  }

  closePdfModal() {
    this.displayViewModal = 'none'
  }
  closeViewModal() {
    this.displayRejectModal = 'none'
  }
  
  download(info: any) {
    // console.log(info);
    this.base64image = this.domSanitizer.bypassSecurityTrustUrl(
      info.file
      );
      this.base64imageName = info.file_name;
  }

  approve() {    
    Swal.fire({
      html: 'Are You Sure?',
      icon: 'warning',
      width: '500px',
      confirmButtonColor: '#005D30',
      confirmButtonText: 'Yes',
      showCancelButton: true,
      cancelButtonColor: '#d33',
    }).then((result) =>{
      if(result.isConfirmed) {
        let approvedData = {
          "id": this.customerId,
          "decision": "approve",
          "staffId": this.staffId.toString(),
          "approverId": this.supervisorId.toString(),
          "comment": "",
          "despondent": "",
          "acct_officer": this.accountdetails.officerName
        };
        this.isLoadingApprove = true;
        this.submit(approvedData);
      }
    });
  }

  reject() {    
    if(this.despondent == null) {
      Swal.fire({
        html: 'Select a Rejection Type',
        icon: 'error',
        width: '500px',
        confirmButtonColor: '#005D30',
        confirmButtonText: 'OK'
      });
    } else if (this.comment.trim().length < 1) {
      Swal.fire({
        html: 'Comment Can Not Be Empty',
        icon: 'error',
        width: '500px',
        confirmButtonColor: '#005D30',
        confirmButtonText: 'OK'
      });
    } else {      
      Swal.fire({
        html: 'Are You Sure?',
        icon: 'warning',
        width: '500px',
        confirmButtonColor: '#005D30',
        confirmButtonText: 'Yes',
        showCancelButton: true,
        cancelButtonColor: '#d33',
      }).then((result) =>{
        if(result.isConfirmed) {
          let rejectedData = {
            "id": this.customerId,
            "decision": "reject",
            "staffId": this.staffId.toString(),
            "approverId": this.supervisorId.toString(),
            "comment": this.comment,
            "despondent": this.despondent.type,
            "acct_officer": this.accountdetails.officerName
          };
          // console.log(rejectedData);
          
          this.isLoadingReject = true;
          this.submit(rejectedData);
        }
      });
    }
  }  

  submit(data: any) {    
    // console.log(data);
    this.apiService.approveOrReject(data)
      .subscribe({ 
        next: (
          (res: any) => {
            // console.log(res);            
            this.isLoadingApprove = false;
            this.isLoadingReject = false;
            if(res.statuscode == '00') {
              Swal.fire({
                html: `${res.statusmessage.toUpperCase()}`,
                icon: 'success',
                width: '500px',
                confirmButtonColor: '#005D30',
                confirmButtonText: 'OK'
              }).then(() => {
                this.displayRejectModal = 'none';
                this.router.navigate(['/pending-request']);
              });
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
            this.isLoadingApprove = false;
            this.isLoadingReject = false;
            this.storage.global_error();         
          }
        )          
    });     
  }

  openRejectModal() {
    this.displayRejectModal = 'block'
  };

  closeRejectModal() {
    this.displayRejectModal = 'none'
  }


  displayViewModal = 'none';
  pdfSrc = '';

  base64image: any = '';
  base64imageName: any = '';
  staffId:any = '';
  supervisorId:any = '';
  accountdetails: any = {};
  accountOfficerNames: any = [];
  
  accountServices: any = [];
  isLoading: Boolean = false;
  displayCorporateCard: Boolean = false;
  // displayAccountOfficerDiv: Boolean = false;
  isLoadingApprove: Boolean = false;
  isLoadingReject: Boolean = false;
  customerId: any = '';

  displayRejectModal: any = 'none';

  comment: string = '';
  despondent: any = null;

  rejectType: any = [
    {
      name: 'Full Rejection',
      type: 'full'
    },
    {
      name: 'Partial Rejection',
      type: 'half'
    }
  ]

  customername: any = '';
  
}
