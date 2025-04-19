

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { VerifyBvnComponent } from '../components/initial-request/01-verify-bvn/verify-bvn.component';
import { AccountSpecificationComponent } from '../components/initial-request/02-account-specification/account-specification.component';
import { AccountSignatoryComponent } from '../components/initial-request/03-account-signatory/account-signatory.component';
import { UploadDocumentComponent } from '../components/initial-request/04-upload-document/upload-document.component';
// import { ReviewInformationComponent } from '../components/initial-request/05-review-information/review-information.component';

import { InitialRequestRoutingModule } from '../routes/initial-request-routing.module';
import { SharedModule } from './shared.module';
import { MaterialModule } from '../material.module';
import { MatInputModule } from '@angular/material/input';

@NgModule({
    declarations: [
        VerifyBvnComponent,
        AccountSpecificationComponent,
        AccountSignatoryComponent,
        UploadDocumentComponent,
        // ReviewInformationComponent,
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