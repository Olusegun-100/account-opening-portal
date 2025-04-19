import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { EncryptionService } from 'src/app/services/encryption.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  
  pendingRequestsCount: number = 0;
  approvedRequestsCount: number = 0;
  rejectedRequestsCount: number = 0;
  partiallyrejectedRequestsCount: number = 0;
  isLoading: Boolean = false;
  imageSpinner: any = this.storage.imageSpinner;

  constructor(
    private storage: StorageService,
    private apiService: ApiService,
    private _e:EncryptionService
  ) {}

  ngOnInit(): void { 
    this.getCount();
  }

  getCount(){
    this.isLoading = true;
    let data = {
      staffId: (sessionStorage.getItem('staffId'))?.toString(),
      supervisorId: (sessionStorage.getItem('supervisorId'))?.toString()
    }
    console.log(data);    
        
    let req = this._e.eD(JSON.stringify(data));

    const request = {
      data: req
    };

    console.log(request);


    this.apiService.getCount(data)
      .subscribe({ 
        next: (
          (res: any) => {

            console.log(res);         
            
          //   let drc = this._e.dD(res.data);
          // console.log(drc);
          
          // console.log(JSON.parse(JSON.parse(drc)));
          // console.log(JSON.stringify(drc));
          // console.log(JSON.parse(drc));
          
          
          // let res = JSON.parse(drc);

          // console.log(response);
            this.isLoading = false;
            if(res.statuscode == '00') {
              this.pendingRequestsCount = res.pendingcounts;
              this.approvedRequestsCount = res.approvedcounts;
              this.rejectedRequestsCount = res.rejectedcounts;
              this.partiallyrejectedRequestsCount = res.rependingcounts;
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
            this.isLoading = false;
            this.storage.global_error();         
          }
        )          
    });  
  }
}