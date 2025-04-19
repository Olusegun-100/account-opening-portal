import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor(
    private ngxService: NgxUiLoaderService
  ) { }

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

    imageSpinner: any = 'assets/images/spin.gif';

    
    timeOutNgxService() {
      setTimeout(() => {
        this.ngxService.stop();
      }, 1);
    }
    
    timeOutNgxBackgroundService() {
      setTimeout(() => {
        this.ngxService.stopBackground();
      }, 1);
    }
}