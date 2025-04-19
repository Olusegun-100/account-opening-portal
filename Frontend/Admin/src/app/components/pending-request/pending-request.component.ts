import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { ApiService } from 'src/app/services/api.service';
import { EncryptionService } from 'src/app/services/encryption.service';
import { StorageService } from 'src/app/services/storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-pending-request',
  templateUrl: './pending-request.component.html',
  styleUrls: ['./pending-request.component.css']
})
export class PendingRequestComponent {


  constructor(
    private storage: StorageService,
    private router: Router,
    private apiService: ApiService,
    private _e: EncryptionService,
    private ngxService: NgxUiLoaderService
  ) {    
  }

  rolename: any = '';
  staffId: any = '';
  ngOnInit(): void { 
    this.staffId = sessionStorage.getItem('staffId');
    this.rolename = sessionStorage.getItem('rolename');
    // console.log(this.rolename);
    this.ngxService.start();
    this.view();
    // this.viewDetails();
  }

  tableData: any = [];
  isLoading: Boolean = false;
  isLoadingSearch: Boolean = false;

  startDate: any = null;
  endDate: any = null;
  searchText: any;

  viewByDate () {
    // if(this.startDate == null ) {
    //   Swal.fire({
    //     html: 'Start Date Can Not Be Null',
    //     icon: 'warning',
    //     width: '500px',
    //     confirmButtonColor: '#005D30',
    //     confirmButtonText: 'OK'
    //   });
    // } else if(this.endDate == null ) {
    //   Swal.fire({
    //     html: 'End Date Can Not Be Null',
    //     icon: 'warning',
    //     width: '500px',
    //     confirmButtonColor: '#005D30',
    //     confirmButtonText: 'OK'
    //   });
    // } else {
      let data: any = {
        status: (this.rolename == 'initiator') ? "" : (this.rolename == 'approver') ? "pending" : '',
        staffId: (this.rolename == 'initiator') ? "" : (this.rolename == 'approver') ? this.staffId : '',
        startDate: this.startDate,
        endDate: this.endDate,
        page: "0"
      };
  
      // console.log(data)
      
      this.isLoadingSearch = true;
      this.apiService.getAccountTableByDate(data).subscribe({
        next: (
          (resp: any) => {
            // console.log(resp);
            this.isLoadingSearch = false;
            this.storage.timeOutNgxService();
            if(resp.statuscode == '00'){
              this.tableData = resp.data;
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
              
              this.isLoadingSearch = false;
              this.storage.timeOutNgxService();
              this.storage.global_error();
          })
      });
    // }
  }  

  view () {
    let data: any = {
      status: (this.rolename == 'initiator') ? "" : (this.rolename == 'approver') ? "pending" : '',
      staffId: (this.rolename == 'initiator') ? "" : (this.rolename == 'approver') ? this.staffId : '', 
    };

    console.log(data);

        
    // let req = this._e.eD(JSON.stringify(data));

    // const request = {
    //   data: req
    // };

    // console.log(request);
    
    this.isLoading = true;
    this.apiService.getAccountTable(data).subscribe({
      next: (
        (resp: any) => {

          console.log(resp);         
            
          //   let drc = this._e.dD(resp.data);
          // console.log(drc);
          
          // console.log(JSON.parse(JSON.parse(drc)));
          // console.log(JSON.stringify(drc));
          // console.log(JSON.parse(drc));
          
          
          // let resp = JSON.parse(drc);

          this.storage.timeOutNgxService();
          if(resp.statuscode == '00'){
            this.tableData = resp.data;
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
          this.storage.timeOutNgxService();
            this.storage.global_error();
        })
    })
  }  

  viewMore(data: any) {
    // console.log(data);
    sessionStorage.setItem('customerid', JSON.stringify(data.cust_id));
    
    this.router.navigate(['/pending-requestdetails']);
  }
}
