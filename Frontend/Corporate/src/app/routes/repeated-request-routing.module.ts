
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';

import { AccountSpecificationRpComponent } from "../components/repeated-request/01-account-specification/account-specification.component";
import { AccountSignatoryRpComponent } from "../components/repeated-request/02-account-signatory/account-signatory.component";

import { UploadDocumentRpComponent } from "../components/repeated-request/03-upload-document/upload-document.component";
// import { ReviewInformationRpComponent } from "../components/repeated-request/04-review-information/review-information.component";

const routes: Routes = [
  { path: 'account-specification/:id', component: AccountSpecificationRpComponent },
  { path: 'account-signatory', component: AccountSignatoryRpComponent },
  { path: 'upload-document', component: UploadDocumentRpComponent },
  // { path: 'review-information', component: ReviewInformationRpComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class RepeatedRequestRoutingModule { }
