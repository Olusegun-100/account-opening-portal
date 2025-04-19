import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { EncryptionService } from 'src/app/services/encryption.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  
  constructor(
    private router: Router,
    private auth: AuthService,
    private _e: EncryptionService,
    private formbuilder: FormBuilder
  ) {}

  loginform: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });
  
  ngOnInit(): void {
    this.loginform = this.formbuilder.group(
      {
        'username': [
          '',
          [
            Validators.required
          ]
        ],
        'password': [
          '',
          [
            Validators.required,
            Validators.maxLength(16)
          ]
        ]
      }
    )    
  }

  login() {
    
    const username = this.loginform.value.username.trim();
    const password = this.loginform.value.password.trim();
    
    let data = {
      username: username,
      password: password
    }
    
    console.log(data);

        
    let req = this._e.eD(JSON.stringify(data));

    const request = {
      data: req
    };

    console.log(request);

    
    this.isLoading = true;
    this.auth.getUserDetails(request).subscribe({
      next: (
        (res: any) => {

          console.log(res);
          

          let drc = this._e.dD(res.data);
          console.log(drc);
          
          // console.log(JSON.parse(JSON.parse(drc)));
          // console.log(JSON.stringify(drc));
          // console.log(JSON.parse(drc));
          
          
          let response = JSON.parse(drc);

          console.log(response);


          console.log(response);  
          this.isLoading = false;     
          if(response.statuscode == '00') {

            if(Object.keys(response.data).length < 1){
              Swal.fire(
                'Invalid Credentials!',
                '',
                'warning'
              );
            } else {
              const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
              });
              Toast.fire({
                icon: 'success',
                title: 'Signed in successfully'
              }).then(() => {
                sessionStorage.setItem('roleId', response.data.roleId);
                sessionStorage.setItem('staffId', response.data.staffId);
                sessionStorage.setItem('rolename', response.data.rolename);
                sessionStorage.setItem('supervisorId', response.data.supervisorId);
                this.auth.setLoggedIn(true);
                this.router.navigate(['']);
              });
            
            }
          } else if(response.statuscode == '96') {
            Swal.fire(
              'Invalid Credentials!',
              '',
              'warning'
            )  
          } else {
            Swal.fire(
              'Something Went Wrong!',
              '',
              'warning'
            )  
          }
        }),
      error: (
        (err: any) => {
          console.log(err); 
          
          this.isLoading = false;
          Swal.fire(
            'Something Went Wrong!',
            '',
            'warning'
          )       
        }
      )
    });
}
  
isLoading: Boolean = false;
imageSpinner: any = 'assets/images/spin.gif';
}
