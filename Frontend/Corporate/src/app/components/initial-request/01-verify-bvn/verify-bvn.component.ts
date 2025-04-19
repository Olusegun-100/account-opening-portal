import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription, interval } from 'rxjs';
import { ApiService } from 'src/app/services/api.service';
import { EncryptionService } from 'src/app/services/encryption.service';
import { StorageService } from 'src/app/services/storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-verify-bvn',
  templateUrl: './verify-bvn.component.html',
  styleUrls: ['./verify-bvn.component.css']
})
export class VerifyBvnComponent implements OnInit  {
  constructor(
    private storage: StorageService,
    private apiService: ApiService,
    private _e: EncryptionService,    
    private router: Router,
    private formBuilder: FormBuilder
    ) { }

    verifybvn: FormGroup = new FormGroup({
      bvn: new FormControl(''),
    });

    verifyotp: FormGroup = new FormGroup({
      otp: new FormControl(''),
    });

    ngOnInit(): void {
      this.verifybvn = this.formBuilder.group(
        {
          bvn: [
            '',
            [
              Validators.required,
              Validators.minLength(11),
              Validators.maxLength(11),
              Validators.pattern("[0-9]{11}")
            ]
          ],
        }
      );

      this.verifyotp = this.formBuilder.group(
        {
          otp: [
            '',
            [
              Validators.required,
              Validators.minLength(6),
              Validators.maxLength(6),
              Validators.pattern("[0-9]{6}")
            ]
          ],
        }
      );
    };
    
    verifyBvn(){      
      if(this.verifybvn.value.bvn.trim().length < 11 ) {
        Swal.fire({
          html: 'BVN LENGTH MUST BE ELEVEN DIGITS',
          icon: 'warning',
          width: '500px',
          confirmButtonColor: '#005D30',
          confirmButtonText: 'OK'
        });
      } else {

        this.isLoadingBvn = true;
        this.showOtpDiv = false;
        sessionStorage.clear();
        this.storage.documents = [];

        if(this.timerSubscription){
          this.timerSubscription.unsubscribe();
        }

        this.apiService.verifyBvn(this.verifybvn.value).subscribe({
          next: (
            (res: any) => {
              // console.log(response);

              let drc = this._e.dD(res.data);
          
              
              let response = JSON.parse(drc);
              

              if(response.statuscode == '00'){

                if(response.data.lastname == null) {
                  
                  Swal.fire({
                    html: 'INVALID BVN',
                    icon: 'warning',
                    width: '500px',
                    confirmButtonColor: '#005D30',
                    confirmButtonText: 'OK'
                  });
                  this.isLoadingBvn = false;

                } else {
                  this.bvnDetails = response.data;
                  sessionStorage.setItem('bvn_info', JSON.stringify(response.data));
                  this.sendOTP();
                }
 
              }
              else if(response.statuscode == '96'){
                this.isLoadingBvn = false;
                this.storage.status_message_error(response);
              }
              else{
                this.isLoadingBvn = false;
                this.storage.global_error();
              }
            }),
            error: (
              err => {
                this.isLoadingBvn = false;
                this.storage.global_error();
              }
          )
        });
      }
    }
    
    timerSubscription!: Subscription;

    countdown: number = 30;
    showResendOtp: Boolean = false;
    showCountDown: Boolean = false;

    sendOTP() {
      let old_phonenumber = this.bvnDetails.phonenumber1;
      if(old_phonenumber[0] == '0') {
        this.new_phonenumber = old_phonenumber.replace(old_phonenumber[0], '234');                    
      }else {
        this.new_phonenumber = this.bvnDetails.phonenumber1;
      };
      
      let sendOtpData: any = {
        msgTo: this.new_phonenumber,
        msgSender: "",
        msgChannels: "",
        to: this.bvnDetails.email,
        subject: "",
        userName: ""
      };
      // console.log(sendOtpData);

      this.countdown = 30;
      
      this.apiService.sendOtp(sendOtpData).subscribe({
        next: (
          (resp: any) => {
            
            this.isLoadingBvn = false;
            
            if(resp.statuscode == '00'){
              
              this.showOtpDiv = true;
              this.showCountDown = true;

              this.timerSubscription = interval(1000).subscribe((seconds) => {
                
                this.countdown = 30 - seconds;

                if (seconds === 30) {
                  this.timerSubscription.unsubscribe();
                  this.showResendOtp = true;
                  this.showCountDown = false;
                };

              });

            }
            else if(resp.statuscode == '96'){
              this.storage.status_message_error(resp);
            }
            else{
              this.storage.global_error();
            }
          }),
          error: (
            err => {
              this.isLoadingBvn = false;
              this.storage.global_error();
            }
        )
      });
    }

    // stopInterval() {
    //   clearInterval()
    // }

    verifyOtp(){
      if(this.verifyotp.value.otp.trim().length < 6 ) {
        Swal.fire({
          html: 'OTP LENGTH MUST BE SIX DIGITS',
          icon: 'warning',
          width: '500px',
          confirmButtonColor: '#005D30',
          confirmButtonText: 'OK'
        });
      } else {

        let verifyOtpData: any = {
          "otp": this.verifyotp.value.otp,
          "sms": this.bvnDetails.phonenumber1,
          "email": this.bvnDetails.email,
        };
  
        // console.log(verifyOtpData);    
        
        this.isLoadingOtp = true;

        this.apiService.verifyOtp(verifyOtpData).subscribe({
          next: (
            (resp: any) => {
              this.isLoadingOtp = false;
              // console.log(resp);
              if(resp.statuscode == '00'){
                this.router.navigate(["/account-specification"]);
              }
              else if(resp.statuscode == '96'){
                this.storage.status_message_error(resp);
              }
              else{
                this.storage.global_error();
              }
            }),
            error: (
              err => {
                this.isLoadingOtp = false;
                this.storage.global_error();
              }
          )
        });
      }
    } 

    bvnDetails: any = [];
    new_phonenumber: any = "";
    xx: number = 60;
    imageSpinner: any = this.storage.imageSpinner;
    imageLogo: any = this.storage.imageLogo;
    
    showOtpDiv: Boolean = false;
    isLoadingBvn: Boolean = false;
    isLoadingOtp: Boolean = false;
    isLoadingResendOtp: Boolean = false;
    displayResendOtpLabel: Boolean = false;

}
