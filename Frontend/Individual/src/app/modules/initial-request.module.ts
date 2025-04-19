

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { VerifyBvnComponent } from '../components/initial-request/01-verify-bvn/verify-bvn.component';
import { PersonalInformationComponent } from '../components/initial-request/02-personal-information/personal-information.component';
import { AccountSpecificationComponent } from '../components/initial-request/03-account-specification/account-specification.component';
import { UploadDocumentComponent } from '../components/initial-request/04-upload-document/upload-document.component';
import { ReviewInformationComponent } from '../components/initial-request/05-review-information/review-information.component';

import { InitialRequestRoutingModule } from '../routes/initial-request-routing.module';
import { SharedModule } from './shared.module';
import { MaterialModule } from '../material.module';
import { MatInputModule } from '@angular/material/input';
// import { AccountSignatoryComponent } from '../components/initial-request/account-signatory/account-signatory.component';

@NgModule({
    declarations: [
        VerifyBvnComponent,
        PersonalInformationComponent,
        AccountSpecificationComponent,
        // AccountSignatoryComponent,
        UploadDocumentComponent,
        ReviewInformationComponent,
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        InitialRequestRoutingModule,
        SharedModule,
        MaterialModule,
        // MatInputModule
    ],
    providers: [],
    schemas: []
})
export class InitialRequestModule { }