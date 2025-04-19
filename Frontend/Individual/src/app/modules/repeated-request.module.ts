

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { PersonalInformationComponent } from '../components/repeated-request/01-personal-information/personal-information.component';
import { AccountSpecificationComponent } from '../components/repeated-request/02-account-specification/account-specification.component';
import { UploadDocumentComponent } from '../components/repeated-request/03-upload-document/upload-document.component';
import { ReviewInformationComponent } from '../components/repeated-request/04-review-information/review-information.component';

import { RepeatedRequestRoutingModule } from '../routes/repeated-request-routing.module';
import { SharedModule } from './shared.module';
import { MaterialModule } from '../material.module';

@NgModule({
  declarations: [
    PersonalInformationComponent,
    AccountSpecificationComponent,
    UploadDocumentComponent,
    ReviewInformationComponent,
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