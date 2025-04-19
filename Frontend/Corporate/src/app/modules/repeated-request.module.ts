

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AccountSpecificationRpComponent } from '../components/repeated-request/01-account-specification/account-specification.component';
import { AccountSignatoryRpComponent } from '../components/repeated-request/02-account-signatory/account-signatory.component';
import { UploadDocumentRpComponent } from '../components/repeated-request/03-upload-document/upload-document.component';
// import { ReviewInformationRpComponent } from '../components/repeated-request/04-review-information/review-information.component';

import { RepeatedRequestRoutingModule } from '../routes/repeated-request-routing.module';
import { SharedModule } from './shared.module';
import { MaterialModule } from '../material.module';

@NgModule({
  declarations: [
    AccountSpecificationRpComponent,
    AccountSignatoryRpComponent,
    UploadDocumentRpComponent,
    // ReviewInformationRpComponent,
  ],
  imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        RepeatedRequestRoutingModule,
        SharedModule,        
        MaterialModule
  ],
  providers: [],
  schemas: []
})
export class RepeatedRequestModule { }